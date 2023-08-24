package co.id.mii.qrscanner.features.transaction.repository

import co.id.mii.qrscanner.core.database.DatabaseDao
import co.id.mii.qrscanner.core.database.entity.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TransactionRepository(val databaseDao: DatabaseDao) {
    fun getTransactionHistory(): Flow<List<TransactionEntity>> = flow<List<TransactionEntity>> {

        val transaction = databaseDao.getAllTransaction()
        emit(transaction)
    }.flowOn(Dispatchers.IO)
}