

package co.id.mii.qrscanner.features.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.shared.component.BottomNav
import co.id.mii.qrscanner.features.home.view.component.HeaderComponen
import co.id.mii.qrscanner.features.home.view.component.HomeMenu
import co.id.mii.qrscanner.ui.theme.titleLarge

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController?=null){
    Scaffold(bottomBar = {
        navController?.let { BottomNav(navController = it) }
    }) {

        Column {
            HeaderComponen()
            Divider()
            Text(modifier = Modifier.padding(20.dp),text = "Menu", style = titleLarge)
            navController?.let { it1 -> HomeMenu(navController = it1) }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}