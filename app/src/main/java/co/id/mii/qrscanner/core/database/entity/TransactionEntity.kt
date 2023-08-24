package co.id.mii.qrscanner.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tbl_transaction")
data class TransactionEntity(

    val bank: String? = null,
    val idTrans: String? = null,
    val merchant: String? = null,
    val value: Double? = 0.0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)