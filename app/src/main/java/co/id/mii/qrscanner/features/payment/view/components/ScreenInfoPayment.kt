package co.id.mii.qrscanner.features.payment.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.shared.component.CloseBtnRight
import co.id.mii.qrscanner.core.utils.numberFormat
import co.id.mii.qrscanner.features.payment.model.TransactionModel
import co.id.mii.qrscanner.ui.theme.titleLarge
import kotlinx.coroutines.delay


@Composable
fun ScreenInfoPayment(navController: NavController?,data : TransactionModel?) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
    ) {

        BodyTransaction(navController, data = data)
    }



}

@Composable
fun BodyTransaction(navController: NavController?, data: TransactionModel?){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (data?.isSucces?:false) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = "Succes",
                    modifier = Modifier.size(150.dp),
                    tint = Color.Green
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "${data?.bank?:"-"}-${data?.idTrans?:"-"}")
                Text(text = data?.merchant?:"-")
                Text(text = "Rp. ${numberFormat(data?.value?:0.0) }", style = titleLarge)
            } else {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "error",
                    modifier = Modifier.size(150.dp),
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "invalid Payment")
            }


        }
        CloseBtnRight(onClose = {
            navController?.popBackStack()
        })
    }

}