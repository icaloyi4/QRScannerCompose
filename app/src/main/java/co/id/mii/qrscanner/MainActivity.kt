@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

package co.id.mii.qrscanner

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        print("Test")
        startKoin {
            androidContext(this@MainActivity)
            modules(
                listOf(
                    netModule,
                    viewModelModule,
                    apiModule,
                    dataStoreModule,
                    databaseModule,
                    repositoryModule
                )
            )
        }
    }

    override fun onPause() {
        super.onPause()
        print("Test")
        stopKoin()
    }

    override fun onStop() {
        super.onStop()
        print("Test")
    }

    override fun onDestroy() {
        super.onDestroy()
        print("Test")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = token
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })


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