package com.vytivskyi.testtaskvideo.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.vytivskyi.testtaskvideo.databinding.ActivityVideoBinding
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoActivity : AppCompatActivity(), Player.Listener {

    private lateinit var binding: ActivityVideoBinding

    private val mViewModel: VideosVM by viewModels()

    @Inject
    lateinit var player: ExoPlayer

    @Inject
    lateinit var playerNotificationManager: PlayerNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra(MainActivity.KEY_POSITION, 0)

        addMp4Files(position)

        binding.videoView.player = player
        player.addListener(this)
        playerNotificationManager.setPlayer(player)
    }

    private fun addMp4Files(position: Int) {
        mViewModel.observeVideos().observe(this@VideoActivity) { listVideo ->
            player.addMediaItems(listVideo.map { video ->
                MediaItem.Builder()
                    .setUri(video.source)
                    .setMediaId(video.uid.toString())
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(video.title)
                            .build()
                    )
                    .build()
            })
            player.seekTo(position, C.INDEX_UNSET.toLong())
            player.prepare()
            player.play()
        }
    }

    override fun onIsLoadingChanged(isLoading: Boolean) {
        super.onIsLoadingChanged(isLoading)
        binding.progressBar.isVisible = isLoading
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        player.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        binding.videoView.player = null
        playerNotificationManager.setPlayer(null)
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

}


