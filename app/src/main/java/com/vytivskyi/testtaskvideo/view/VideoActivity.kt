package com.vytivskyi.testtaskvideo.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.vytivskyi.testtaskvideo.databinding.ActivityVideoBinding
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity : AppCompatActivity(), Player.Listener {

    private lateinit var player: ExoPlayer
    private val mViewModel: VideosVM by viewModels()

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra(MainActivity.KEY_POSITION, 0)

        setupPlayer()
        addMp4Files(position)
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.videoView.player = player
        player.addListener(this)
    }

    private fun addMp4Files(position: Int) {
        mViewModel.observeVideos().observe(this@VideoActivity) { listVideo ->
            player.addMediaItems(listVideo.map {
                MediaItem.Builder()
                    .setUri(it.source)
                    .setMediaId(it.uid.toString())
                    .build()
            })
            player.seekTo(position, C.INDEX_UNSET.toLong())
            player.prepare()
            player.play()
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            else -> {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        player.stop()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}
