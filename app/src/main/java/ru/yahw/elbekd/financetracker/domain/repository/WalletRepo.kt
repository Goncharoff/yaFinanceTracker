package ru.yahw.elbekd.financetracker.domain.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.WalletDataBase
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import javax.inject.Inject

/**
 * Created by Elbek D. on 29.07.2018.
 */
class WalletRepo @Inject constructor(val application: Application) {

    private val walletDataBase: WalletDataBase = WalletDataBase.getInstance(application)

    private val walletData: LiveData<List<WalletData>> = walletDataBase.walletDataDao().getAllWalets()

    fun getAllWallets() = walletData

    fun insertWallet(walletData: WalletData) {
        Completable.fromAction { walletDataBase.walletDataDao().insertWallet(walletData) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }


    fun wallet(name: String): LiveData<WalletData> = MutableLiveData<WalletData>().apply { value = walletData.value!!.find { it.walletName == name } }//walletDao.addWallet(name)

    fun getCurrenciesType(): LiveData<List<String>> = MutableLiveData<List<String>>().apply { value = aviabileWallets }

    fun getWalletsNames(): LiveData<List<String>> = walletDataBase.walletDataDao().selectAllWalletsName()

    fun getTimeTypes(): LiveData<List<String>> = MutableLiveData<List<String>>().apply { value = aviabileTime }

    private companion object {
        val aviabileTime: List<String> = listOf("Week", "Month")
        val aviabileWallets: List<String> = listOf("RUB", "USD")
    }
}