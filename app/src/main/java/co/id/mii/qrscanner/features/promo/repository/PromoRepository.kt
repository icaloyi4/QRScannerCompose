package co.id.mii.qrscanner.features.promo.repository

import android.util.Log
import co.id.mii.qrscanner.core.network.ApiClient
import co.id.mii.qrscanner.core.network.BaseResponse
import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow

class PromoRepository(val apiClient: ApiClient) {

    fun getPromo() = flow<BaseResponse<List<BankPromoResponse>>> {
        try {
            val promoResponse = apiClient.getPromo()
            if (promoResponse.isSuccessful) {
                val baseResponse = BaseResponse<List<BankPromoResponse>>(code = promoResponse.code(), message = "Success", data = promoResponse.body()?: arrayListOf())
                emit(baseResponse)
            } else {

                emit(BaseResponse<List<BankPromoResponse>>(code = promoResponse.code(), message = promoResponse.message(), data = arrayListOf()))
            }
        } catch (e : Exception) {
            Log.e("APIPROMO", e.message.toString())
            emit(BaseResponse<List<BankPromoResponse>>(code = 500, message = e.message, data = arrayListOf()))
        }


    }.flowOn(Dispatchers.IO)

}