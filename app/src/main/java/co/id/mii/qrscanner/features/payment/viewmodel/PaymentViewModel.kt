package co.id.mii.qrscanner.features.payment.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import co.id.mii.qrscanner.core.routes.RoutesModel
import co.id.mii.qrscanner.features.payment.model.TransactionModel
import co.id.mii.qrscanner.features.payment.repository.PaymentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PaymentViewModel(val repo : PaymentRepository) : ViewModel() {

    private val _laodingState = mutableStateOf<Boolean>(false)
    val loadingState : State<Boolean>  = _laodingState

    fun payQR(barcode : String, navController: NavController) {
        viewModelScope.launch {
            _laodingState.value = true
            var transactionModel : TransactionModel? = TransactionModel()
           if (barcode == "BNI.ID12345678.MERCHANT MOCK TEST.50000"){
               val barcodeSplit = barcode.split(".")
               transactionModel = TransactionModel(bank = barcodeSplit[0], idTrans = barcodeSplit[1], merchant = barcodeSplit[2], value = barcodeSplit[3].toDouble())
                repo.payQR(transactionModel).collect() {
                   transactionModel.isSucces = it
               }
               navController.navigate("${RoutesModel.screeninfo}/${transactionModel.toString()}"){
                   popUpTo(RoutesModel.home){
                       inclusive = true
                   }
               }

           }
            _laodingState.value = false
        }
    }
}