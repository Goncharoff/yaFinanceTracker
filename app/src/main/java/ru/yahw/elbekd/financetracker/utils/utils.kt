package ru.yahw.elbekd.financetracker.utils

import java.math.BigDecimal
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat

/**
 * Created by Elbek D. on 31.07.2018.
 */
private val decimalFormatSymbols = DecimalFormatSymbols().apply {
    decimalSeparator = '.'
    groupingSeparator = ','
}

private val decimalFormat = DecimalFormat("0.00", decimalFormatSymbols)

fun formatDecimalNumber(v: Double): String = decimalFormat.format(v)


fun formatBigNumber(v: BigDecimal): String = decimalFormat.format(v)

fun getDateFromML(milSec: Long, format: DateFormat): String {

    return format.format(milSec)
}