package com.vytivskyi.testtaskvideo.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.vytivskyi.testtaskvideo.databinding.ActivityVideoBinding
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoActivity : AppCompatActivity(), Player.Listener {
    private lateinit var newItems: MutableList<MediaItem>

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private val mViewModel: VideosVM = VideosVM()

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getVideos()
        }



        val position = intent.getIntExtra("position", 0)
        Log.d("lol", position.toString())

        setupPlayer()
        addMp4Files(position)
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = binding.videoView
        playerView.player = player
        player.addListener(this)
    }

    private fun addMp4Files(position: Int) {
        newItems = mutableListOf()
        mViewModel.videos.observe(this@VideoActivity) {
            it?.let {
                for ( i in it.indices) {
                    newItems.add(
                        MediaItem.Builder().setUri(it[i].sources[0]).setMediaId(it[i].title)
                            .build(),
                    )
                }
                player.addMediaItems(newItems)
                player.seekTo(position, C.INDEX_UNSET.toLong());
                player.playWhenReady = true;
            }
        }

    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }
}
