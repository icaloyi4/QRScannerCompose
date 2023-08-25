package co.id.mii.qrscanner.features.portofolio.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.id.mii.qrscanner.core.utils.numberFormat
import co.id.mii.qrscanner.features.portofolio.model.TransferData
import co.id.mii.qrscanner.ui.theme.titleLarge

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListTransactionPortofolio(transferData: List<TransferData>) {
    Column() {
        Text(text = "Transfer List", style = titleLarge)
        FlowColumn() {
            transferData.forEach(){ data ->
                Text(text = "${data.trxDate} Rp. ${numberFormat(data.nominal?.toDouble()?:0.0)}", modifier = Modifier.padding(10.dp))
            }

        }
    }
}