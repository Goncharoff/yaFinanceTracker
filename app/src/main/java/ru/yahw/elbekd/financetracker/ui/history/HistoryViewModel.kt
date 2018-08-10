package ru.yahw.elbekd.financetracker.ui.history

import android.arch.lifecycle.ViewModel
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import javax.inject.Inject

/**
 * Created by Elbek D. on 28.07.2018.
 */
class HistoryViewModel @Inject constructor(private val transactionRepo: TransactionRepo) : ViewModel(){
    fun getAllTransactionData() = transactionRepo.getAllTransactionData()
}