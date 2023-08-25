package co.id.mii.qrscanner.core.routes

import co.id.mii.qrscanner.R

sealed class MenuItem(var title: String, var icon: Int?, var screen_route: String) {
    object Promo : MenuItem("Promo", R.drawable.ic_local_offer, RoutesModel.promo)
    object Payment : MenuItem("Pay", R.drawable.ic_qr_code_example, RoutesModel.payment)
    object Portofolio : MenuItem("Portofolio", R.drawable.ic_insert_chart, RoutesModel.portofolio)
    object Transaction :
        MenuItem("Transaction History", R.drawable.ic_list_alt, RoutesModel.transaction)

    object Home : MenuItem("Home", R.drawable.ic_home_filled, RoutesModel.home)
    object ScreenInfo : MenuItem("Screen Info", null, RoutesModel.screeninfo)

    object Notification : MenuItem("Notification", R.drawable.ic_circle_notifications, RoutesModel.notification)
}