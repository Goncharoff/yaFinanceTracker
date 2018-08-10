package ru.yahw.elbekd.financetracker.data.db.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData

@Dao
interface TransactionDataDao {
    @Query("SELECT * FROM transactionData")
    fun selectAllTransactions(): LiveData<List<TransactionData>>

    @Query("SELECT * FROM transactionData WHERE wallet_name = :name")
    fun selectTransactionByWalletName(name: String): LiveData<MutableList<TransactionData>>

    @Insert(onConflict = REPLACE)
    fun insertTransaction(transactionData: TransactionData)

    @Query("DELETE FROM transactionData WHERE id = :id")
    fun deleteAllFromWallets(id: Long)
}