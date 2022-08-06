package com.vytivskyi.testtaskvideo

import android.app.PendingIntent
import android.graphics.Bitmap
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager.BitmapCallback
import com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter


private class DescriptionAdapter : MediaDescriptionAdapter {
  override fun getCurrentContentTitle(player: Player): String {
    return getCurrentContentTitle(player)
  }

  override fun getCurrentContentText(player: Player): String? {
    return getCurrentContentText(player)
  }


  override fun getCurrentLargeIcon(player: Player, callback: BitmapCallback): Bitmap? {
   return null
  }

  override fun createCurrentContentIntent(player: Player): PendingIntent? {
    return createCurrentContentIntent(player)
  }
}