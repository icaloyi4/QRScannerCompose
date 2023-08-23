package co.id.mii.qrscanner.features.payment.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentScreen(navController: NavController?=null) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Scan QR Code")
    }
}

@Preview
@Composable
fun PaymentPreview(){
    PaymentScreen()
}