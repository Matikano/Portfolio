package com.matikano.complimentapp.data.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.matikano.complimentapp.MainActivity
import com.matikano.complimentapp.MainActivity.Companion.EXTRA_KEY_COMPLIMENT
import com.matikano.complimentapp.R
import com.matikano.complimentapp.domain.notification.NotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ComplimentNotificationService @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationService {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(content: String) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(EXTRA_KEY_COMPLIMENT, content)
        }
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CODE_GO_TO_ACTIVITY,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, COMPLIMENT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_compliment)
            .setContentTitle(context.getString(R.string.compliment_notification_title))
            .setContentText(content)
            .setContentIntent(activityPendingIntent)
            .build()


        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val COMPLIMENT_CHANNEL_ID = "compliment_channel"
        const val CHANNEL_DESCRIPTION = "Used for displaying daily compliment notifications"
        const val CHANNEL_NAME = "compliments"

        const val NOTIFICATION_ID = 1
        const val REQUEST_CODE_GO_TO_ACTIVITY = 1

    }

}