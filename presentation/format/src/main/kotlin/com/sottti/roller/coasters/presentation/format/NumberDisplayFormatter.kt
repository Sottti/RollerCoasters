package com.sottti.roller.coasters.presentation.format

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.format.mapper.toLocale
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

internal fun Double.toDisplayFormat(
    appLanguage: AppLanguage,
    defaultLocale: Locale,
): String {
    val locale = appLanguage.toLocale(defaultLocale)
    val symbols = DecimalFormatSymbols(locale)
    val formatter = DecimalFormat("#,##0.###", symbols)
    val decimalSeparator = Regex.escape(symbols.decimalSeparator.toString())
    return formatter
        .format(this)
        .replace("${decimalSeparator}0+$".toRegex(), "")
}