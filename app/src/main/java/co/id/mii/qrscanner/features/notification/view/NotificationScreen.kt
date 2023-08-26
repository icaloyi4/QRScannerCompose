package co.id.mii.qrscanner.features.notification.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.id.mii.qrscanner.core.shared.component.TopAppBarShared
import co.id.mii.qrscanner.features.notification.viewmodel.NotificationViewModel
import co.id.mii.qrscanner.features.payment.view.components.PreviewViewComposable
import co.id.mii.qrscanner.ui.theme.titleLarge
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(navController: NavHostController) {
    val mv : NotificationViewModel = koinViewModel()
    val activity = LocalContext.current as Activity
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    Scaffold(topBar = {
        TopAppBarShared(title = "Notification Test", backCallback = {
            navController.popBackStack()
        }, bgColor = null, backIcon = true)
    }) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationPermission(permissionState)
        }

        if (!permissionState.hasPermission) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               Text(
                    text = "You dont have permission to test notification",
                    modifier = Modifier.padding(top = 48.dp)
                )
                Button(
                    onClick = {
                        permissionState.launchPermissionRequest()
                    }
                ) {
                    Text(text = "Click to give permission")
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {

                        mv.sendNotification(activity)
                    }
                ) {
                    Text(text = "Test Notification")
                }
            }
        }
    }

}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermission(
    permissionState: PermissionState,
) {
    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { /* ... */ },
        permissionNotAvailableContent = { /* ... */ }
    ) {
        // Open Camera
    }
}
