package co.id.mii.qrscanner.core.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.id.mii.qrscanner.features.home.view.HomeScreen
import co.id.mii.qrscanner.features.payment.model.TransactionModel
import co.id.mii.qrscanner.features.payment.model.TransactionTypeArgs
import co.id.mii.qrscanner.features.payment.view.PaymentScreen
import co.id.mii.qrscanner.features.payment.view.components.ScreenInfoPayment
import co.id.mii.qrscanner.features.transaction.view.TransactionScreen
import com.google.gson.Gson

@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoutesModel.home) {
        composable(RoutesModel.home) {
            HomeScreen(navController)
        }
        composable(RoutesModel.payment) {
            PaymentScreen(navController)
        }
        composable(RoutesModel.transaction) {
            TransactionScreen(navController)
        }
        composable(route = "${RoutesModel.screeninfo}/{transaction}",
            arguments = listOf(
                navArgument("transaction") {
                    type = TransactionTypeArgs()
                }
            )) {
            ScreenInfoPayment(
                navController,data = it.arguments?.getString("transaction")
                    ?.let { data -> Gson().fromJson(data, TransactionModel::class.java) })
        }
    }
}