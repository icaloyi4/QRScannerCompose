package co.id.mii.qrscanner.features.transaction.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.id.mii.qrscanner.core.database.entity.TransactionEntity
import co.id.mii.qrscanner.core.utils.numberFormat
import co.id.mii.qrscanner.ui.theme.labelSmall

@Preview
@Composable
fun ItemTransaction(transactionEntity: TransactionEntity) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Text(text = "${transactionEntity.idTrans}", style = labelSmall)
        Row {
            Text(text = "${transactionEntity.merchant}", style = labelSmall, modifier = Modifier.wrapContentWidth())
            Text(text = "Rp. ${numberFormat((transactionEntity.value?:0.0))}", style = labelSmall, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
        }

    }
}