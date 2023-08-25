package co.id.mii.qrscanner.features.portofolio.repository

import co.id.mii.qrscanner.core.other.dummyChartString
import co.id.mii.qrscanner.features.portofolio.model.DonutChartDataModel
import co.id.mii.qrscanner.features.portofolio.model.LineChartDataModel
import co.yml.charts.common.extensions.isNotNull
import com.google.gson.Gson
import org.json.JSONArray

class PortofolioRepository() {

    fun getDonutData(): List<DonutChartDataModel> {
        try {
            val obj = JSONArray(dummyChartString)
            for (index in 0 until obj.length()) {
                if (obj.get(index).isNotNull()) {
                    val objectData = obj.getJSONObject(index)
                    if (objectData.get("type").equals("donutChart")) {

                        return Gson().fromJson(
                            objectData.get("data").toString(),
                            Array<DonutChartDataModel>::class.java
                        ).toList()
                    } else {
                        continue
                    }
                } else {
                    continue
                }
            }
            return arrayListOf()
        } catch (e: Exception) {
            return arrayListOf()
        }

    }

    fun getLineData(): LineChartDataModel? {
        try {
            val obj = JSONArray(dummyChartString)
            for (index in 0 until obj.length()) {
                if (obj.get(index).isNotNull()) {
                    val objectData = obj.getJSONObject(index)
                    if (objectData.get("type").equals("lineChart")) {

                      return Gson().fromJson(
                            objectData.get("data").toString(),
                            LineChartDataModel::class.java
                        )
                    } else {
                        continue
                    }
                } else {
                    continue
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}