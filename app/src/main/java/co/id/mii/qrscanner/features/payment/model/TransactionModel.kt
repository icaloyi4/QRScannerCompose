package co.id.mii.qrscanner.features.payment.model

import android.net.Uri
import com.google.gson.Gson


data class TransactionModel(val bank:String, val idTrans:String, val merchant : String, val value : Double, var isSucces : Boolean? = false){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}