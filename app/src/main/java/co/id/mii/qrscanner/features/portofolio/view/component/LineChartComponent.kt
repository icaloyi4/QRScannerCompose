package co.id.mii.qrscanner.features.portofolio.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.id.mii.qrscanner.ui.theme.titleLarge
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.LineChartData

@Composable
fun LineChartComponent(lineChartData: LineChartData) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Line Chart", style = titleLarge)
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )
    }
}

