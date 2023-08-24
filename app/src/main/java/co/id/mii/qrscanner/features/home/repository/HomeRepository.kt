package co.id.mii.qrscanner.features.home.repository

import co.id.mii.qrscanner.core.database.PreferenceModel
import co.id.mii.qrscanner.core.database.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class HomeRepository(val preff : PreferencesManager) {
    fun getSaldo() : Flow<Double> = flow<Double> {
        var saldo = 1000000000.0
        var savedSaldo = preff.getData(PreferenceModel.SALDO_KEY, defaultValue = "")
        if (savedSaldo.isEmpty()) {
            preff.saveData(PreferenceModel.SALDO_KEY, saldo.toString())
        } else {
            savedSaldo.toDoubleOrNull()?.let {
                saldo = it
            }
        }
        emit(saldo)
    }.flowOn(Dispatchers.IO)
}