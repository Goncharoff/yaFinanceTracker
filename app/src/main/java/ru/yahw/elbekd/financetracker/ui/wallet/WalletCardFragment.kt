package ru.yahw.elbekd.financetracker.ui.wallet

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.cardview_wallet.*
import kotlinx.android.synthetic.main.fragment_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.R.id.account_title
import ru.yahw.elbekd.financetracker.adapters.SwipeToDeleteCallback
import ru.yahw.elbekd.financetracker.adapters.TransactionRVAdapter
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.data.db.entities.WalletData
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.domain.repository.ICurrencyInfoRepository
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.utils.formatDecimalNumber


class WalletCardFragment : BaseFragment<WalletViewModel>(), Injectable {
    public override fun getLayoutId() = R.layout.cardview_wallet

    var rvTransactionAdapter: TransactionRVAdapter? = null
    var walletPagerAdapter: WalletPagerAdapter? = null

    companion object {
        const val WALLET_NAME_EXTRA = "WALLET_NAME"

        fun newInstance(args: Bundle) = WalletCardFragment().apply { arguments = args }
    }

    private lateinit var vm: WalletViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vm = getViewModel()
        val v = inflater.inflate(R.layout.cardview_wallet, container, false)

        vm.getWalletByName(arguments!!.getString(WALLET_NAME_EXTRA, "unknown"))
                .observe(this, Observer { wallet ->
                    wallet?.let {
                        bindWallet(v, it)
                        setupTransactionRVAdapter(wallet.walletName)
                    }
                })




        return v
    }


    private fun bindWallet(v: View, wallet: WalletData) {
        account_title.text = wallet.walletName
        tv_main_currency_name.text = wallet.mainCurrency
        vm.getWalletTransactions(wallet.walletName).observe(this, Observer {
            it?.let {
                val remainder = it.asSequence().sumByDouble { it.amount.toDouble() }
                tv_main_currency_value.text = formatDecimalNumber(remainder)
                vm.getCurrencyCalut().subscribe({ result ->
                    tv_secondary_currency_value.text = formatDecimalNumber(tv_main_currency_value.text.toString().toDouble() / result.Valute.USD.Value)
                })
                setupPieChart(v, it)
            }
        })


    }

    private fun setupPieChart(v: View, transactions: List<TransactionData>) {
        val expenses = transactions.filter { it.amount.toFloat() < 0 }
        val overallAmount = expenses.asSequence().map { -it.amount.toFloat() }.sum()

        val list = mutableListOf<PieEntry>()
        expenses.forEach { list.add(PieEntry(overallAmount / -it.amount.toFloat(), it.type)) }

        val pieDataSet = PieDataSet(list, getString(R.string.all_expense))
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        pieDataSet.setDrawValues(false)

        with(v.findViewById<PieChart>(R.id.cardview_wallat_piechart)) {
            data = PieData(pieDataSet)
            invalidate()
        }
    }

    private fun setupTransactionRVAdapter(walletName: String) {

        vm.getAllTransactionsByName(walletName).observe(this, Observer {
            it?.let { list ->
                if (rv_transaction_list.adapter == null) {
                    rv_transaction_list.layoutManager = LinearLayoutManager(context)
                    rvTransactionAdapter = TransactionRVAdapter(list, context)
                    rv_transaction_list.adapter = rvTransactionAdapter
                    rvTransactionAdapter?.updateList(list.reversed())

                    val swipeHandler = object : SwipeToDeleteCallback(context!!) {
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            vm.deleteTransactionById(rvTransactionAdapter!!.getCurrentItemDBId(viewHolder.adapterPosition))
                            rvTransactionAdapter?.removeItem(viewHolder.adapterPosition)
                        }
                    }
                    val itemTouchHelper = ItemTouchHelper(swipeHandler)
                    itemTouchHelper.attachToRecyclerView(rv_transaction_list)

                } else {
                    rvTransactionAdapter?.updateList(list.reversed())
                }

            }
        })
    }


}