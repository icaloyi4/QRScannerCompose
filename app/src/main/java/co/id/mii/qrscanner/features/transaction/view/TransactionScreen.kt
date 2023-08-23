
package co.id.mii.qrscanner.features.transaction.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.ui.theme.QRScannerComposeTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionScreen(navController: NavController?=null) {
    QRScannerComposeTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "TransactionHistory")})
        }) {
            Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {

            }
        }
    }

}

@Preview
@Composable
fun TransactionScreenPreview(){
    TransactionScreen()
}
