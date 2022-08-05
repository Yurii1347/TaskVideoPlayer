package com.vytivskyi.testtaskvideo.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.vytivskyi.testtaskvideo.databinding.ActivityVideoBinding
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoActivity : AppCompatActivity(), Player.Listener {
    private lateinit var newItems: MutableList<MediaItem>

    private lateinit var player: ExoPlayer
    private val mViewModel: VideosVM by viewModels()

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        Log.d("lol", position.toString())

        setupPlayer()
        addMp4Files(position)

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putLong("seekTime", player.currentPosition)
        outState.putInt("mediaItem", player.currentMediaItemIndex)
        Log.d("lol", player.currentPosition.toString())

    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.videoView.player = player
        player.addListener(this)
    }

    private fun addMp4Files(position: Int) {
        newItems = mutableListOf()
        mViewModel.videos.observe(this@VideoActivity) {
            it?.let {
                for (i in it.indices) {
                    newItems.add(
                        MediaItem.Builder().setUri(it[i].sources[0]).setMediaId(it[i].title)
                            .build(),
                    )
                }
                player.addMediaItems(newItems)
                player.seekTo(position, C.INDEX_UNSET.toLong())
                player.prepare()
                player.play()
            }
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
