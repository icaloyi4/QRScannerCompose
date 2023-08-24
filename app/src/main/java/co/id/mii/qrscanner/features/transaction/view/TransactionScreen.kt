
package co.id.mii.qrscanner.features.transaction.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import co.id.mii.qrscanner.features.transaction.view.component.ItemTransaction
import co.id.mii.qrscanner.features.transaction.viewmodel.TransactionViewModel
import co.id.mii.qrscanner.ui.theme.QRScannerComposeTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionScreen(navController: NavController?=null) {
    val mv : TransactionViewModel = koinViewModel()
    QRScannerComposeTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "TransactionHistory")}, navigationIcon = {
                Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "back", modifier = Modifier.clickable {
                    navController?.popBackStack()
                })
            })
        }) {
            if(mv.loadingState.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn{
                    items(mv.listTransactionState.value) {
                        ItemTransaction(it)
                        Divider()
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun TransactionScreenPreview(){
    TransactionScreen()
}
