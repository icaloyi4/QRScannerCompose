package co.id.mii.qrscanner.features.notification.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.routes.RoutesModel
import co.id.mii.qrscanner.core.shared.component.CloseBtnRight

@Composable
fun ResultScreen(navController: NavController, title : String, transactionCode : String){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "$title")
            Text(text = "$transactionCode")
        }
        CloseBtnRight {
            navController.navigate(RoutesModel.home)
        }
    }
}