package co.id.mii.qrscanner.core.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.id.mii.qrscanner.features.home.view.HomeScreen
import co.id.mii.qrscanner.features.payment.view.PaymentScreen
import co.id.mii.qrscanner.features.transaction.view.TransactionScreen

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = RoutesModel.home){
        composable(RoutesModel.home){
            HomeScreen(navController)
        }
        composable(RoutesModel.payment){
            PaymentScreen(navController)
        }
        composable(RoutesModel.transaction){
            TransactionScreen(navController)
        }
    }
}