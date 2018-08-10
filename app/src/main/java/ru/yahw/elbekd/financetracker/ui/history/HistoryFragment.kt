package ru.yahw.elbekd.financetracker.ui.history

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_about.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.adapters.HistoryTransactionRVAdapter
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class HistoryFragment : BaseFragment<HistoryViewModel>(), Injectable {


    companion object {
        val TAG: String = HistoryFragment::class.java.simpleName
        fun newInstance() = HistoryFragment()
    }

    private lateinit var vm: HistoryViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = getViewModel()

        setupRvAdapter()
    }

    override fun getLayoutId() = R.layout.fragment_about

    fun setupRvAdapter() {
        vm.getAllTransactionData().observe(this, Observer {
            it?.let { list ->
                rv_transaction_history.layoutManager = LinearLayoutManager(context)
                rv_transaction_history.adapter = HistoryTransactionRVAdapter(list, context)
            }
        })
    }
}

