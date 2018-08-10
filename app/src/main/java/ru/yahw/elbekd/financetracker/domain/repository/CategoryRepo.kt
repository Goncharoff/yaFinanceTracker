package ru.yahw.elbekd.financetracker.domain.repository

import android.arch.lifecycle.MutableLiveData
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.domain.model.Category
import javax.inject.Inject


class CategoryRepo @Inject constructor() {
    private companion object {
        val types = listOf(
                Category("Food", R.color.food, R.drawable.ic_category_food),
                Category("Health", R.color.health, R.drawable.ic_category_health),
                Category("Home", R.color.home, R.drawable.ic_category_home)
        )
    }

    fun categories() = MutableLiveData<List<Category>>().apply { value = types }
}