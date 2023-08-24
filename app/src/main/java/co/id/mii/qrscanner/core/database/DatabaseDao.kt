package co.id.mii.qrscanner.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import co.id.mii.qrscanner.core.database.entity.TransactionEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransaction(transaction: TransactionEntity)
    @Query("select * from tbl_transaction")
    fun getAllTransaction(): List<TransactionEntity>


}