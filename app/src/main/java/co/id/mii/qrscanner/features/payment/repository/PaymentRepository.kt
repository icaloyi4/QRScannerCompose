package co.id.mii.qrscanner.features.payment.repository

import co.id.mii.qrscanner.core.database.DatabaseDao
import co.id.mii.qrscanner.core.database.PreferenceModel
import co.id.mii.qrscanner.core.database.PreferencesManager
import co.id.mii.qrscanner.core.database.entity.TransactionEntity
import co.id.mii.qrscanner.features.payment.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PaymentRepository(val preff: PreferencesManager, val database: DatabaseDao) {
    fun payQR(data: TransactionModel) = flow<Boolean> {
        var isSucces = false
        try {
            database.addTransaction(TransactionEntity(data.bank, data.idTrans, data.merchant, data.value))
            var saldo = preff.getData(PreferenceModel.SALDO_KEY, defaultValue = "")
            if (saldo.isEmpty()){
                preff.saveData(PreferenceModel.SALDO_KEY, (1000000000-(data.value?:0.0)).toString())
            } else {
                preff.saveData(PreferenceModel.SALDO_KEY, (saldo.toDouble()-(data.value?:0.0)).toString())
            }
            isSucces = true
        } catch (e : Exception){

        }

        emit(isSucces)
    }.flowOn(Dispatchers.IO)
}