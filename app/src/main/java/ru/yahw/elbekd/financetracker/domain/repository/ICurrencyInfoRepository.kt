package ru.yahw.elbekd.financetracker.domain.repository

import ru.yahw.elbekd.financetracker.data.remote.CurrencyCourseAPI


object ICurrencyInfoRepository {
    fun provideCurrencyCurseValue(): CurrencyInfoRepository {
        return CurrencyInfoRepository(CurrencyCourseAPI.create())
    }
}