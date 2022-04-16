package com.fionicholas.moviecatalogue.core.utils.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Double?.formatRatingBar(): Float {
    return (this?.div(2))?.toFloat()!!
}

fun String.toDisplayFormat(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    var myDate: Date? = null
    try {
        myDate = dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return newFormat.format(myDate!!)
}

fun Int?.orZero(): Int = this ?: 0