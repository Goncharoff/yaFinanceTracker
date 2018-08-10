package ru.yahw.elbekd.financetracker.domain.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Completable
import io.reactivex.internal.operators.completable.CompletableAmb
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.WalletDataBase
import ru.yahw.elbekd.financetracker.data.db.daos.TransactionDataDao
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class TransactionRepo @Inject constructor(val application: Application) {

    private val walletDataBase: WalletDataBase = WalletDataBase.getInstance(application)

    private val transactionDao: TransactionDataDao = walletDataBase.transactionDataDao()

    fun getAllTransactionData(): LiveData<List<TransactionData>> = transactionDao.selectAllTransactions()
    fun getAllTransactionsByName(name: String): LiveData<MutableList<TransactionData>> = transactionDao.selectTransactionByWalletName(name)
    fun deleteTransactionById(id: Long) {
        Completable.fromAction { transactionDao.deleteAllFromWallets(id) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun commitTransaction(t: TransactionData) {
        Completable.fromAction { transactionDao.insertTransaction(t) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

}