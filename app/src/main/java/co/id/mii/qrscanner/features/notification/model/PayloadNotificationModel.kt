package co.id.mii.qrscanner.features.notification.model

import com.google.gson.annotations.SerializedName

class PayloadNotificationModel {
    @SerializedName("message" ) var message : Message? = Message()
}

data class Message (

    @SerializedName("data"         ) var data         : DataPayload?         = DataPayload(),
    @SerializedName("notification" ) var notification : NotificationPayload? = NotificationPayload(),
    @SerializedName("android"      ) var android      : AndroidData?      = AndroidData(),
    @SerializedName("apns"         ) var apns         : Apns?         = Apns(),
    @SerializedName("token"        ) var token        : String?       = null

)

data class DataPayload (

    @SerializedName("deepLink" ) var deepLink : String? = null

)

data class NotificationPayload (

    @SerializedName("title" ) var title : String? = null,
    @SerializedName("body"  ) var body  : String? = null

)

data class NotificationImage (

    @SerializedName("image" ) var image : String? = null

)

data class AndroidData (

    @SerializedName("notification" ) var notification : NotificationImage? = NotificationImage()

)

data class Aps (

    @SerializedName("category"        ) var category        : String? = null,
    @SerializedName("mutable-content" ) var mutableContent : Int?    = null

)

data class Payload (

    @SerializedName("aps" ) var aps : Aps? = Aps()

)

data class Apns (

    @SerializedName("payload"     ) var payload    : Payload?    = Payload(),
    @SerializedName("fcm_options" ) var fcmOptions : FcmOptions? = FcmOptions()

)

data class FcmOptions (

    @SerializedName("image" ) var image : String? = null

)