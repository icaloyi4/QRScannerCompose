package co.id.mii.qrscanner.features.promo.repository

import co.id.mii.qrscanner.core.network.ApiClient
import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow

class PromoRepository(val apiClient: ApiClient) {

    fun getPromo() = flow<List<BankPromoResponse>> {
        val promoResponse = apiClient.getPromo()
        if (promoResponse.isSuccessful) {
            emit(promoResponse.body()?: arrayListOf())
        } else {
            emit(arrayListOf())
        }

    }.flowOn(Dispatchers.IO)

}