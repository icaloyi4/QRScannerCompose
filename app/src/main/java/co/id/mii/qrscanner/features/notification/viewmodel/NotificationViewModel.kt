package co.id.mii.qrscanner.features.notification.viewmodel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import co.id.mii.qrscanner.MainActivity
import co.id.mii.qrscanner.R
import co.id.mii.qrscanner.core.other.dummyPayloadString
import co.id.mii.qrscanner.features.notification.model.PayloadNotificationModel
import co.id.mii.qrscanner.features.notification.repository.NotificationRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.gson.Gson
import java.util.Random

class NotificationViewModel(val repo : NotificationRepository) : ViewModel() {
     fun sendNotification(context : Context) {

         val payload = Gson().fromJson(dummyPayloadString, PayloadNotificationModel::class.java)

        val intent = Intent(Intent.ACTION_VIEW, payload.message?.data?.deepLink?.toUri(), context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
         val pending: PendingIntent? =
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
             TaskStackBuilder.create(context).run {
                 addNextIntentWithParentStack(intent)
                 getPendingIntent(0, PendingIntent.FLAG_MUTABLE)
             }
         } else {
             TaskStackBuilder.create(context).run {
                 addNextIntentWithParentStack(intent)
                 getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
             }
         }



         val channelId = context.getString(R.string.default_notification_channel_id)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(payload.message?.notification?.title)
            .setContentText(payload.message?.notification?.body)
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