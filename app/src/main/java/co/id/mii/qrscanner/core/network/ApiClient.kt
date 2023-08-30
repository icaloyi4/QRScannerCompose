package co.id.mii.qrscanner.core.network

import co.id.mii.qrscanner.features.promo.model.BankPromoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiClient {

//    401 Jika diberi header
//    @Headers("Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjc1OTE0MTUwLCJleHAiOjE2Nzg1MDYxNTB9.TcIgL5CDZYg9o8CUsSjUbbUdsYSaLutOWni88ZBs9S8")
    @GET("/promos")
    suspend fun getPromo() : Response<List<BankPromoResponse>>

}