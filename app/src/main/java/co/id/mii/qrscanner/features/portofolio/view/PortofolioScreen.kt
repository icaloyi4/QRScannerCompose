package co.id.mii.qrscanner.features.portofolio.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import co.id.mii.qrscanner.core.shared.component.TopAppBarShared
import co.id.mii.qrscanner.features.portofolio.view.component.DonutChartComponent
import co.id.mii.qrscanner.features.portofolio.view.component.LineChartComponent
import co.id.mii.qrscanner.features.portofolio.viewmodel.PortofolioViewModel
import org.koin.androidx.compose.koinViewModel


@ExperimentalLayoutApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PortofolioScreen(navController: NavHostController?) {
    val mv : PortofolioViewModel = koinViewModel()
    Scaffold(topBar = {
        TopAppBarShared(title = "My Portofolio", backCallback = { navController?.popBackStack() }, bgColor = null, backIcon = true)
    }) {


        LazyColumn(){
            item {
                mv.donutDataState.value?.let {
                    if(it.slices.isNotEmpty()){
                        DonutChartComponent(donutChartData = it, mv)
                    }

                }
            }
            item {
                mv.lineDataState.value?.let {
                    if (it.linePlotData.lines.isNotEmpty()){
                        LineChartComponent(lineChartData = it)
                    }

                }
            }
        }


    }
}