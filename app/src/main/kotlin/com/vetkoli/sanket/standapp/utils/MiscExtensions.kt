package com.vetkoli.sanket.standapp.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sanket on 19/12/17.
 */


const val DEFAULT_DATE_FORMAT = "dd MMM yy"

fun Long.toDateString(dateFormat: String = DEFAULT_DATE_FORMAT): String {
    val date = Date(this)
    val dateFormatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return dateFormatter.format(date)
}