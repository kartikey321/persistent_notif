package com.example.persistent_notif

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHandler {
    private const val CHANNEL_ID = "your_channel_id"

    private const val ACTION_OPEN_FLUTTER_PAGE = "action_open_flutter_page"
    private const val EXTRA_METHOD_CALL = "method_call"
    fun createPermanentNotification(context: Context) {
        createNotificationChannel(context)

        // Create an explicit intent to open the Flutter screen
        val collapsedView = RemoteViews(
           context.packageName,
            R.layout.notification_collapsed
        )
        val expandedView = RemoteViews(
            context.packageName,
            R.layout.notification_expanded
        )

        val actionIntent = Intent(context, MainActivity::class.java)
        actionIntent.action = ACTION_OPEN_FLUTTER_PAGE
        val extras = Bundle()
        extras.putString(EXTRA_METHOD_CALL, "executeFunctionInFlutter")
        actionIntent.putExtras(extras)
        val actionPendingIntent = PendingIntent.getActivity(
            context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        collapsedView.setOnClickPendingIntent(R.id.button, actionPendingIntent)



        expandedView.setImageViewResource(
            R.id.image_view_expanded,
            R.drawable.launch_background
        )


        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.launch_background)
            .setCustomContentView(collapsedView)
           .setAutoCancel(false)
            .setOngoing(true) //  .setCustomBigContentView(expandedView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()



        // Show the notification
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Permanent Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
