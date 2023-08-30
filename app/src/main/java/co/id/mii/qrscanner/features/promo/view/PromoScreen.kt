package co.id.mii.qrscanner.features.promo.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.shared.component.BottomNav
import co.id.mii.qrscanner.features.promo.view.component.PromoItem
import co.id.mii.qrscanner.features.promo.viewmodel.PromoViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PromoScreen(navController: NavController?=null) {
    val mv : PromoViewModel = koinViewModel()
    Scaffold(bottomBar = {
        navController?.let { BottomNav(navController = it) }
    }) {
        Box(
            modifier = Modifier.padding(bottom =  40.dp)
        ) {
            if(mv.loadingState.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                if(mv.listPromoState.value.isNotEmpty()){
                    LazyVerticalGrid(columns = GridCells.Fixed(1), ) {
                        items(mv.listPromoState.value) {
                            navController?.let { it1 -> PromoItem(it1,it) }
                        }

                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No Promo Found This Time")
                    }
                }

            }
        }



    }
}