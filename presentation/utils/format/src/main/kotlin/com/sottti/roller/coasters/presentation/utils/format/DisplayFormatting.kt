package com.sottti.roller.coasters.presentation.utils.format

public fun Double.toDisplayFormat(): String =
    toBigDecimal()
        .stripTrailingZeros()
        .toPlainString()