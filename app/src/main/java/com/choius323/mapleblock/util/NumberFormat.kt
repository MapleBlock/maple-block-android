package com.choius323.mapleblock.util

import java.text.DecimalFormat

object NumberFormat {
    val NUMBER_FORMAT = DecimalFormat("#,###")
}

fun Int.toNumString() = NumberFormat.NUMBER_FORMAT.format(this)