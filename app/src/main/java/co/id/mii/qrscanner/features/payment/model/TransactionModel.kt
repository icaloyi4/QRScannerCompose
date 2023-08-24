package co.id.mii.qrscanner.features.payment.model

import android.net.Uri
import com.google.gson.Gson


data class TransactionModel(val bank:String?=null, val idTrans:String?=null, val merchant : String?=null, val value : Double?=0.0, var isSucces : Boolean? = false){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}