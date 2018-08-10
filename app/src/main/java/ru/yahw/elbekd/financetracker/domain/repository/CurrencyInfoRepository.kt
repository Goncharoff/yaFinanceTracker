package ru.yahw.elbekd.financetracker.domain.repository

import io.reactivex.Single
import ru.yahw.elbekd.financetracker.data.remote.CurrencyCourseAPI
import ru.yahw.elbekd.financetracker.domain.model.CurrencyModel

class CurrencyInfoRepository(private val currencyCourseAPI: CurrencyCourseAPI) {

    fun usdCourseValue(): Single<CurrencyModel.Data> {
        return currencyCourseAPI.getCourse()
    }


}