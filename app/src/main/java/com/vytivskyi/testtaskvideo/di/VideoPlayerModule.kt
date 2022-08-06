package com.vytivskyi.testtaskvideo.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.DefaultMediaDescriptionAdapter
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.vytivskyi.testtaskvideo.R
import com.vytivskyi.testtaskvideo.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "PLAYER_NOTIFICATION_CHANNEL"

@Module
@InstallIn(SingletonComponent::class)
object ExoPlayerModule {

    @Provides
    fun provideExoplayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context)
            .setRenderersFactory(DefaultRenderersFactory(context))
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }

    @Provides
    fun providePlayerNotificationManager(@ApplicationContext context: Context): PlayerNotificationManager {
        return PlayerNotificationManager.Builder(context, NOTIFICATION_ID, CHANNEL_ID)
            .setMediaDescriptionAdapter(DefaultMediaDescriptionAdapter(getPendingIntent(context)))
            .setChannelNameResourceId(R.string.channel_title)
            .setChannelDescriptionResourceId(R.string.channel_description)
            .build()
    }

   private fun getPendingIntent(context: Context): PendingIntent {
        return PendingIntent.getActivity(
            context, 777, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
    }

}