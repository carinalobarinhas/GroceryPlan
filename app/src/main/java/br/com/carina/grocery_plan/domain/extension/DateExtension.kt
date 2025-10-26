package br.com.carina.grocery_plan.domain.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatDate(): String {
    val format = "dd/MM/yyyy"
    val to = SimpleDateFormat(format, Locale.getDefault())
    var dateApp: String = ""
    try {
        dateApp = to.format(this)
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
    return dateApp
}