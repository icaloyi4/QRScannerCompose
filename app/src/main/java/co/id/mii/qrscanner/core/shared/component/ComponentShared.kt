package co.id.mii.qrscanner.core.shared.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.id.mii.qrscanner.core.routes.MenuItem
import co.id.mii.qrscanner.ui.theme.PrimaryOrange
import co.id.mii.qrscanner.ui.theme.SecondaryOrange
import co.id.mii.qrscanner.ui.theme.labelSmall
import co.id.mii.qrscanner.ui.theme.titleLarge
import kotlinx.coroutines.delay


@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        MenuItem.Home,
        MenuItem.Promo,
    )

    Box {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            backgroundColor = PrimaryOrange
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon!!),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = SecondaryOrange,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopCenter, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            OutlinedButton(
                onClick = {
                    navController.navigate(MenuItem.Payment.screen_route)
                },
                modifier = Modifier.size(75.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, PrimaryOrange),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryOrange)
            ) {
                Icon(
                    painterResource(id = MenuItem.Payment.icon!!),
                    contentDescription = MenuItem.Payment.title,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }


}

@Composable
fun CloseBtnRight(onClose : ()->Unit){
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(10.dp)) {
        Icon(imageVector = Icons.Sharp.Close, contentDescription = null, tint = Color.Black, modifier = Modifier.clickable { onClose() })
    }
}

@Composable
fun TopAppBarShared(title : String, backIcon : Boolean? = false, backCallback : () -> Unit, bgColor: Color?){
    TopAppBar(title = { Text(text = title?:"", color = Color.White)}, backgroundColor = bgColor?: PrimaryOrange, navigationIcon = {
        if(backIcon == true) {
            Icon(imageVector = Icons.Sharp.ArrowBack, tint = Color.White,  contentDescription = null, modifier = Modifier.clickable {
                backCallback()
            })
        }

    })
}


