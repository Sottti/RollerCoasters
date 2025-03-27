package com.sottti.roller.coasters.presentation.utils.format

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.utils.format.mapper.toLocale
import java.util.Locale

internal fun Double.toDisplayFormat(
    appLanguage: AppLanguage,
    defaultLocale: Locale,
): String {
    val formatter = DecimalFormat("#,###").apply { maximumFractionDigits = 3 }
    val symbols = DecimalFormatSymbols(appLanguage.toLocale(defaultLocale))
    formatter.decimalFormatSymbols = symbols
    val decimalSeparator = Regex.escape(symbols.decimalSeparator.toString())
    return formatter.format(this)
        .replace("(\\d*[1-9])0+$".toRegex(), "$1")
        .replace("^0${decimalSeparator}0+$".toRegex(), "0")
}