package co.id.mii.qrscanner.features.notification.viewmodel

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import co.id.mii.qrscanner.MainActivity
import co.id.mii.qrscanner.R
import co.id.mii.qrscanner.core.notification.NotificationService
import co.id.mii.qrscanner.features.notification.repository.NotificationRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Random

class NotificationViewModel(val repo : NotificationRepository) : ViewModel() {
     fun sendNotification(context : Context) {

        val intent = Intent(Intent.ACTION_VIEW, "sample.id://transfer/result?title=SUKSES&transactionCode=RF001-204".toUri(), context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

         val pending: PendingIntent? = TaskStackBuilder.create(context).run {
             addNextIntentWithParentStack(intent)
             getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
         }


         val channelId = context.getString(R.string.default_notification_channel_id)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Test Bosqu")
            .setContentText("Test Ini Bosqu")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pending)

        val manager = context.getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "TEST BNI", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        manager.notify(Random().nextInt(), notificationBuilder.build())
    }

}