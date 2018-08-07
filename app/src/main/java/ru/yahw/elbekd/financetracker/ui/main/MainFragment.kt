package ru.yahw.elbekd.financetracker.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.preference.R.attr.min
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cardview_wallet.*
import kotlinx.android.synthetic.main.cardview_wallet.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.adapters.TransactionRVAdapter
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletCardFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletPagerAdapter
import ru.yahw.elbekd.financetracker.ui.wallet.operations.NewWalletDialogFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.PeriodicOperationFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionDialogFragment

/**
 * Created by Elbek D. on 22.07.2018.
 */
class MainFragment : BaseFragment<MainFragmentViewModel>(), Injectable {
    private var isFABMenuOpen: Boolean = false

    var rvTransactionAdapter: TransactionRVAdapter? = null

    companion object {
        val TAG: String = MainFragment::class.java.simpleName
        fun newInstance() = MainFragment()
    }

    private lateinit var vm: MainFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()

        vm.getAllWallets().observe(this, Observer {
            it?.let { list ->
                val fragmentList = mutableListOf<Fragment>()
                list.forEach { w ->
                    fragmentList.add(WalletCardFragment
                            .newInstance(Bundle().apply { putString(WalletCardFragment.WALLET_NAME_EXTRA, w.walletName) }))
                }
                cardview_wallet_pager.adapter = WalletPagerAdapter(childFragmentManager, fragmentList)
            }
        })

        setupFabButtons()
        setClickFabButtons()
    }


    override fun getLayoutId() = R.layout.fragment_main

    private fun setupFabButtons() = fab.setOnClickListener {
        if (!isFABMenuOpen) {
            expandFAB()
        } else {
            closeFab()
        }
    }


    private fun setClickFabButtons() {
        fab_transaction.setOnClickListener { TransactionDialogFragment.newInstance().show(fragmentManager, null) }
        fab_add_wallet.setOnClickListener { NewWalletDialogFragment.newInstance().show(fragmentManager, null) }
        fab_pereodic_transaction.setOnClickListener { PeriodicOperationFragment.newInstance().show(fragmentManager, null) }
    }

    private fun expandFAB() {
        isFABMenuOpen = true

        fab_transaction.animate().translationY((-resources.getDimension(R.dimen.animate_fab_gravity_Y_115)))
        fab_add_wallet.animate().translationY((-resources.getDimension(R.dimen.animate_fab_gravity_Y_165)))
        fab_pereodic_transaction.animate().translationY(-resources.getDimension(R.dimen.animate_fab_gravity_Y_215))
    }

    private fun closeFab() {
        isFABMenuOpen = false

        fab_transaction.animate().translationY(resources.getDimension(R.dimen.animate_fab_gravity_Y_115))
        fab_add_wallet.animate().translationY(resources.getDimension(R.dimen.animate_fab_gravity_Y_165))
        fab_pereodic_transaction.animate().translationY(resources.getDimension(R.dimen.animate_fab_gravity_Y_215))


    }


    private fun setupTransactionRVAdapter(walletName: String) {

        vm.getAllTransactionsByName(walletName).observe(this, Observer {

            it?.let { list ->
                if (rv_transaction_list.adapter == null) {
                    rv_transaction_list.layoutManager = LinearLayoutManager(context)
                    rvTransactionAdapter = TransactionRVAdapter(list.reversed().subList(0, minOf(5, list.size)), context)
                    rv_transaction_list.adapter = rvTransactionAdapter
                } else {
                    rv_transaction_list.adapter!!.notifyDataSetChanged()
                }

            }
        })
    }

}