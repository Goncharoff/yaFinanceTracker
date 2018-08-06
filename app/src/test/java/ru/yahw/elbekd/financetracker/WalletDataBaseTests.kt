package ru.yahw.elbekd.financetracker

import org.junit.Test
import org.assertj.core.api.Assertions.*
import ru.yahw.elbekd.financetracker.data.db.WalletDataBase
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData

class WalletDataBaseTests{
    val walletData : WalletData = WalletData("Cash", "Cash", "RUB", "250")
    @Test
    fun checkDataBaseName(){
        assertThat("wallet.db").isEqualTo(WalletDataBase.DB_NAME)
    }

    @Test
    fun checkStandartWallet(){
        assertThat(WalletDataBase.stubWallets).isEqualTo(walletData)
    }


}