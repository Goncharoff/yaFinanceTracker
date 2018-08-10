package ru.yahw.elbekd.financetracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.transaction_history_list.view.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.utils.getDateFromML
import java.text.SimpleDateFormat

class HistoryTransactionRVAdapter(private val inputData: List<TransactionData>, private val context: Context?) : RecyclerView.Adapter<HistoryTransactionRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_history_list, p0, false))


    }

    override fun getItemCount(): Int = inputData.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvTransactionSum.text = String.format(inputData[p1].amount.toString())
        p0.tvTransactionDate.text =  getDateFromML(inputData[p1].date, SimpleDateFormat("dd.MM.yyyy"))
        p0.tvTransactionType.text = inputData[p1].type
        p0.tvWalletName.text = inputData[p1].walletName
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTransactionType = view.tv_transaction_type_history
        val tvTransactionSum = view.tv_transaction_sum_history
        val tvTransactionDate = view.tv_transaction_date_item_history
        val tvWalletName = view.tv_transaction_wallet_history
    }
}