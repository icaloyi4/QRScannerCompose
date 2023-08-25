package co.id.mii.qrscanner.features.promo.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.shared.component.CloseBtnRight
import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import co.id.mii.qrscanner.ui.theme.titleLarge
import coil.compose.rememberAsyncImagePainter

//@Preview
@Composable
fun PromoDetailScreen(navController: NavController?, data : BankPromoResponse?){
    Surface(modifier = Modifier.fillMaxSize()) {

        Column {
            Image(
                painter = rememberAsyncImagePainter("${data?.img?.formats?.medium?.url}", filterQuality = FilterQuality.High),
                contentDescription = null,
                modifier = Modifier
                    .width((data?.img?.formats?.medium?.width?:200).dp),
                contentScale = ContentScale.FillWidth
            )
            Column (modifier = Modifier.padding(10.dp)) {
                Text(text = data?.nama?:"-", style = titleLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Promo Detail")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = data?.desc?:"-")
            }


        }
        CloseBtnRight(onClose = {
            navController?.popBackStack()
        })
    }
}