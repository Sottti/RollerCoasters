package com.sottti.roller.coasters.presentation.utils

public fun Double.toDisplayFormat(): String =
    toBigDecimal()
        .stripTrailingZeros()
        .toPlainString()