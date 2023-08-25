package co.id.mii.qrscanner.features.portofolio.viewmodel

import android.graphics.Color as clr
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.mii.qrscanner.features.portofolio.model.DonutChartDataModel
import co.id.mii.qrscanner.features.portofolio.model.TransferData
import co.id.mii.qrscanner.features.portofolio.repository.PortofolioRepository
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.piechart.models.PieChartData
import kotlinx.coroutines.launch
import java.util.Random

class PortofolioViewModel(val repo: PortofolioRepository) : ViewModel() {
    private val _loadingState = mutableStateOf<Boolean>(false)
    val loadingState: State<Boolean> = _loadingState

    private val _listDonutData = arrayListOf<DonutChartDataModel>()

    private val _donutData = mutableStateOf<PieChartData?>(null)

    var donutDataState: State<PieChartData?> = _donutData

    private val _lineData = mutableStateOf<LineChartData?>(null)

    var lineDataState: State<LineChartData?> = _lineData

    private val _selectedTransfer = mutableStateOf<List<TransferData>?>(null)

    var selectedTransferState: State<List<TransferData>?> = _selectedTransfer

    init {
        fetchData()
    }

    fun selectedTransferData(slice: PieChartData.Slice) {
        val dataindex = _donutData.value?.slices?.indexOf(slice)
        dataindex?.let {
            if (_listDonutData.isNotEmpty()) _selectedTransfer.value = _listDonutData[it].data
        }


    }

    private fun fetchData() {
        viewModelScope.launch {
            _loadingState.value = true
            _donutData.value = buildDonutData()
            _lineData.value = buildLineData()
            _loadingState.value = false
        }

    }

    private fun buildDonutData(): PieChartData {

        _listDonutData.clear()
        _listDonutData.addAll(repo.getDonutData())

        val listDataDonut = arrayListOf<PieChartData.Slice>()
        repeat(_listDonutData.size) { i ->
            val rnd = Random()
            val color = clr.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            listDataDonut.add(
                PieChartData.Slice(
                    _listDonutData[i].label.toString(),
                    (_listDonutData[i].percentage ?: "0.0").toFloat(),
                    Color(color),
                    sliceDescription = { it ->
                        it.toString()
                    })
            )
        }

        return PieChartData(
            slices = listDataDonut, plotType = PlotType.Donut
        )
    }

    private fun buildLineData(): LineChartData {

        val lineData = repo.getLineData()
        val pointsData: ArrayList<Point> =
            arrayListOf()
        repeat(lineData?.month?.size ?: 0) { index ->
            lineData?.month?.get(index)?.let {
                pointsData.add(Point(index.toFloat(), it.toFloat(), description = ""))
            }

        }

        val xAxisData = AxisData.Builder()
            .axisStepSize(100.dp)
            .steps(pointsData.size - 1)
            .labelData { i -> i.toString() }
            .labelAndAxisLinePadding(15.dp)
            .build()

        val yAxisData = AxisData.Builder()
            .labelData { i ->
                i.toString()
            }.build()

        return LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = pointsData,
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            backgroundColor = Color.White
        )
    }
}