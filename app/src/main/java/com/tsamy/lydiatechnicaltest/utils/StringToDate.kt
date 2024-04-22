package com.tsamy.lydiatechnicaltest.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun stringToDate(dateISO: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    return LocalDate.parse(dateISO, formatter).toString()
}