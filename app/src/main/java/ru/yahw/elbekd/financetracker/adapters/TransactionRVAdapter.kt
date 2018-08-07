package ru.yahw.elbekd.financetracker.adapters

import android.arch.lifecycle.LiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.transaction_list_item.view.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData

class TransactionRVAdapter(private val items: List<TransactionData>, private val context: Context?) : RecyclerView.Adapter<TransactionRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvTransactionType?.text = items[p1].type
        p0.tvTransactionSum?.text = String.format(items[p1].amount.toString())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTransactionType = view.tv_transaction_type
        val tvTransactionSum = view.tv_transaction_sum
    }

}