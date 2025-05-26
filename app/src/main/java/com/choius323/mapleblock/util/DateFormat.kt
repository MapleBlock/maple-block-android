package com.choius323.mapleblock.util

import org.threeten.bp.format.DateTimeFormatter

object DateFormat {
    val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd")!!
    val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd HH:mm:ss")!!
    val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")!!
}