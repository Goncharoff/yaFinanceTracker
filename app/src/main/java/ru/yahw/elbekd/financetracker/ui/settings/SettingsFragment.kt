package ru.yahw.elbekd.financetracker.ui.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.di.Injectable
import ru.yahw.elbekd.financetracker.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Elbek D. on 22.07.2018.
 */
class SettingsFragment : BaseFragment<SettingsViewModel>(), Injectable {
    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
        fun newInstance() = SettingsFragment()
    }
    private lateinit var vm: SettingsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()

    }

    override fun getLayoutId() = R.layout.fragment_settings

}