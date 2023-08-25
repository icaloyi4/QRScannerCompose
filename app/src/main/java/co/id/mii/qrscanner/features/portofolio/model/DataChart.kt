package co.id.mii.qrscanner.features.portofolio.model

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName


data class DonutChartDataModel (

    @SerializedName("label"      ) var label      : String?         = null,
    @SerializedName("percentage" ) var percentage : String?         = null,
    @SerializedName("data"       ) var data       : ArrayList<TransferData> = arrayListOf(),
    var color : Color? = Color.Black

)

data class TransferData(

    @SerializedName("trx_date" ) var trxDate : String? = null,
    @SerializedName("nominal"  ) var nominal : Int?    = null

)

data class LineChartDataModel (

    @SerializedName("month" ) var month : ArrayList<Int> = arrayListOf()

)