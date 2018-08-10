package ru.yahw.elbekd.financetracker.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Adapter
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.cardview_wallet.*
import kotlinx.android.synthetic.main.cardview_wallet.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.adapters.SwipeToDeleteCallback
import ru.yahw.elbekd.financetracker.adapters.TransactionRVAdapter
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletCardFragment
import ru.yahw.elbekd.financetracker.ui.wallet.WalletPagerAdapter
import ru.yahw.elbekd.financetracker.ui.wallet.operations.NewWalletDialogFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.PeriodicOperationFragment
import ru.yahw.elbekd.financetracker.ui.wallet.operations.TransactionDialogFragment


class MainFragment : BaseFragment<MainFragmentViewModel>(), Injectable {
    private var isFABMenuOpen: Boolean = false

    var rvTransactionAdapter: TransactionRVAdapter? = null
    var walletPagerAdapter: WalletPagerAdapter? = null

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
                walletPagerAdapter = WalletPagerAdapter(childFragmentManager, fragmentList)
                cardview_wallet_pager.adapter = walletPagerAdapter
            }
        })

        setupFabButtons()
        setClickFabButtons()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = getViewModel()

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


}


