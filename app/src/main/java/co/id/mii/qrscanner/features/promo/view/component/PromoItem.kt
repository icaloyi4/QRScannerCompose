package co.id.mii.qrscanner.features.promo.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.routes.RoutesModel
import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import coil.compose.rememberAsyncImagePainter

@Composable
fun PromoItem(navController: NavController, bankPromoResponse: BankPromoResponse) {
    Card(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp), elevation = 10.dp, modifier = Modifier.padding(10.dp)) {
        Column {
            Image(
                painter = rememberAsyncImagePainter("${bankPromoResponse.img?.formats?.medium?.url}", filterQuality = FilterQuality.High),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Divider()
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                Text(text = bankPromoResponse.nama?:"-", modifier = Modifier.fillMaxWidth().weight(1f))
                Button(onClick = { navController.navigate("${RoutesModel.promodetail}/${bankPromoResponse.toString()}") }) {
                    Text(text = "Detail")
                }
            }

        }

    }
}