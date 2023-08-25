@file:OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

package co.id.mii.qrscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import co.id.mii.qrscanner.core.injection.apiModule
import co.id.mii.qrscanner.core.injection.dataStoreModule
import co.id.mii.qrscanner.core.injection.databaseModule
import co.id.mii.qrscanner.core.injection.netModule
import co.id.mii.qrscanner.core.injection.repositoryModule
import co.id.mii.qrscanner.core.injection.viewModelModule
import co.id.mii.qrscanner.core.routes.NavigationGraph
import co.id.mii.qrscanner.ui.theme.QRScannerComposeTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin{
            androidContext(this@MainActivity)
            modules(listOf(netModule, viewModelModule,apiModule,dataStoreModule,databaseModule,repositoryModule))
        }
        setContent {
            QRScannerComposeTheme {
                // A surface container using the 'background' color from the theme
                RootScreen()
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun RootScreen() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        NavigationGraph(navController = navController)
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QRScannerComposeTheme {
        RootScreen()
    }
}