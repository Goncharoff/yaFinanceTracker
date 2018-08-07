package ru.yahw.elbekd.financetracker.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.yahw.elbekd.financetracker.data.db.daos.TransactionDataDao
import ru.yahw.elbekd.financetracker.data.db.daos.WalletDataDao
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData

const val DB_NAME = "wallet.db"

val stubWallets = WalletData("Cash", "Cash", "RUB", "250")

@Database(entities = [WalletData::class, TransactionData::class], version = 1)
@TypeConverters(Convectors::class)
abstract class WalletDataBase : RoomDatabase() {


    abstract fun walletDataDao(): WalletDataDao
    abstract fun transactionDataDao(): TransactionDataDao

    companion object {

        private var instance: WalletDataBase? = null


        fun getInstance(context: Context): WalletDataBase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, WalletDataBase::class.java, DB_NAME)
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Completable.fromAction { getInstance(context).walletDataDao().insertWallet(stubWallets) }
                                        .subscribeOn(Schedulers.io())
                                        .subscribe()
                            }
                        })
                        .build()

    }


}

