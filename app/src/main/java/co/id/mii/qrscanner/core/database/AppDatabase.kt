package co.id.mii.qrscanner.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.id.mii.qrscanner.core.database.entity.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val databaseDao: DatabaseDao
}