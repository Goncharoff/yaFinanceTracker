package ru.yahw.elbekd.financetracker.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.transaction_list_item.view.*
import ru.yahw.elbekd.financetracker.R
import ru.yahw.elbekd.financetracker.data.db.entities.TransactionData
import ru.yahw.elbekd.financetracker.utils.getDateFromML
import java.math.BigDecimal
import java.text.SimpleDateFormat

class TransactionRVAdapter(private val items: MutableList<TransactionData>, private val context: Context?) : RecyclerView.Adapter<TransactionRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        var size = 0
        if (items.size <= 4) {
            size = items.size
        } else {
            size = 4
        }
        return size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvTransactionType?.text = items[p1].type
        if(items[p1].amount > BigDecimal.ZERO){
            p0.tvTransactionSum?.setTextColor(Color.GREEN)
            p0.tvTransactionSum?.text = String.format(items[p1].amount.toString())
        } else {
            p0.tvTransactionSum?.setTextColor(Color.RED)
            p0.tvTransactionSum?.text = String.format(items[p1].amount.toString())
        }

        p0.tvTransactionDate?.text = getDateFromML(items[p1].date, SimpleDateFormat("dd.MM.yyyy"))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTransactionType = view.tv_transaction_type
        val tvTransactionSum = view.tv_transaction_sum
        val tvTransactionDate = view.tv_transaction_date_item
    }

    fun getCurrentItemDBId(position: Int): Long {
        return items[position].id!!
    }

    fun updateList(data: List<TransactionData>) {
        items.clear()
        for (i in 0..data.size - 1) {
            items.add(data[i])
        }
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}