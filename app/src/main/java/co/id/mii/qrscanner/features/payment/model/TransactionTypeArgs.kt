package co.id.mii.qrscanner.features.payment.model

import co.id.mii.qrscanner.core.base.JsonNavType
import com.google.gson.Gson

class TransactionTypeArgs : JsonNavType<TransactionModel>() {
    override fun fromJsonParse(value: String): TransactionModel = Gson().fromJson(value, TransactionModel::class.java)
    override fun TransactionModel.getJsonParse(): String =Gson().toJson(this)


}