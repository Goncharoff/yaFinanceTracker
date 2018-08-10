package ru.yahw.elbekd.financetracker.ui.wallet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.domain.repository.CurrencyInfoRepository
import ru.yahw.elbekd.financetracker.domain.repository.ICurrencyInfoRepository
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
// TODO retrieve from repo and update
class WalletViewModel @Inject constructor(
        private val walletRepo: WalletRepo,
        private val transactionRepo: TransactionRepo
) : ViewModel() {

    fun getWalletTransactions(walletName: String) = transactionRepo.getAllTransactionsByName(walletName)
    fun getAllTransactionData() = transactionRepo.getAllTransactionData()
    fun getWalletByName(name: String) = walletRepo.wallet(name)

    fun getAllTransactionsByName(name: String) = transactionRepo.getAllTransactionsByName(name)

    fun deleteTransactionById(id: Long) = transactionRepo.deleteTransactionById(id)


    fun convertCurrency(from: String, to: String) = walletRepo.getExchangeRate(from, to)


    fun getCurrencyCalut() = repository
    private val repository = ICurrencyInfoRepository.provideCurrencyCurseValue()
            .usdCourseValue()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}
