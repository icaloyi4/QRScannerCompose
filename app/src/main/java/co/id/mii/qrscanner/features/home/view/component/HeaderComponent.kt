package co.id.mii.qrscanner.features.home.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.id.mii.qrscanner.ui.theme.titleLarge

@Preview
@Composable
fun HeaderComponen(){
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = "Selamat Siang, Rizky Haris Risaldi", style = titleLarge)
        Text(text = "Saldo anda saat ini : ")
        Text(text = "Rp. 1.000.0000")
    }
}