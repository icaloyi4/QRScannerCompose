package co.id.mii.qrscanner.features.promo.model

import co.id.mii.qrscanner.core.base.JsonNavType
import com.google.gson.Gson

class BankPromoTypeArgs : JsonNavType<BankPromoResponse>() {
    override fun fromJsonParse(value: String): BankPromoResponse = Gson().fromJson(value, BankPromoResponse::class.java)
    override fun BankPromoResponse.getJsonParse(): String = Gson().toJson(this)


}