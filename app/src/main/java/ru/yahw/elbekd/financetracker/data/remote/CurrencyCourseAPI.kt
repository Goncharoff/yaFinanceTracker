package ru.yahw.elbekd.financetracker.data.remote

import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.yahw.elbekd.financetracker.domain.model.CurrencyModel

interface CurrencyCourseAPI {

    @GET("/daily_json.js")
    fun getCourse(): Single<CurrencyModel.Data>

    companion object Factory {
        fun create(): CurrencyCourseAPI {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://www.cbr-xml-daily.ru")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


            return retrofit.create(CurrencyCourseAPI::class.java)
        }
    }
}