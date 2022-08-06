package com.vytivskyi.testtaskvideo.view

import android.app.PendingIntent
import android.graphics.Bitmap
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager.BitmapCallback
import com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter


var mediaDescriptorInstance: MediaDescriptionAdapter = object : MediaDescriptionAdapter {
    override fun getCurrentContentTitle(player: Player): CharSequence {
        return "<Media Main Title>"
    }


    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        return null
    }

    override fun getCurrentContentText(player: Player): CharSequence? {
        return "<Media sub-Title>"
    }


    override fun getCurrentLargeIcon(player: Player, callback: BitmapCallback): Bitmap? {
        // Icon to display
        return null
    }
}