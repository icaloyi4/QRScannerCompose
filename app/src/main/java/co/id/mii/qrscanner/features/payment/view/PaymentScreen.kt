package co.id.mii.qrscanner.features.payment.view

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.routes.RoutesModel
import co.id.mii.qrscanner.core.shared.component.CloseBtnRight
import co.id.mii.qrscanner.features.payment.model.TransactionModel
import co.id.mii.qrscanner.features.payment.view.components.PreviewViewComposable
import co.id.mii.qrscanner.features.payment.viewmodel.PaymentViewModel
import co.id.mii.qrscanner.ui.theme.titleLarge
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@androidx.camera.core.ExperimentalGetImage
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentScreen(navController: NavController? = null) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val mv : PaymentViewModel = koinViewModel()
    Surface(modifier = Modifier.fillMaxSize()) {

        CameraPermission(permissionState = permissionState)
        if (permissionState.hasPermission) {

            PreviewViewComposable(){
//                Toast.makeText(context, "Barcode found", Toast.LENGTH_SHORT).show()
//                navController?.navigate("${RoutesModel.screeninfo}/${TransactionModel(idTrans = "1234", value = 10000000.0, merchant = "Coba Coba", bank = "BNI", isSucces = true).toString()}"){
//                    popUpTo(RoutesModel.home){
//                        inclusive = true
//                    }
//                }
                navController?.let { it1 -> mv.payQR(it, it1) }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .size(250.dp, 250.dp)
                        .drawBehind {
                            drawRoundRect(
                                color = Color.Red, style = Stroke(
                                    width = 2f,
                                    pathEffect = PathEffect.cornerPathEffect(8.dp.toPx())
                                )
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                }
                Text(
                    text = "Scan QR Code to Pay", style = titleLarge, modifier = Modifier.padding(10.dp)
                )
            }


        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You dont have permission to using camera",
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
        }
        CloseBtnRight(onClose = {
            navController?.popBackStack()
        })



    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermission(
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

@androidx.camera.core.ExperimentalGetImage
@Preview
@Composable
fun PaymentPreview() {
    PaymentScreen()
}