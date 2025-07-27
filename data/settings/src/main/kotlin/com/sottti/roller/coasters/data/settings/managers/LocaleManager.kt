package com.sottti.roller.coasters.data.settings.managers

import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale
import javax.inject.Inject

internal class LocaleManager @Inject constructor(
    private val localeProvider: LocaleProvider,
) {
    val appLocale: Locale
        get() = localeProvider.getAppLocale()

    val systemLocale: Locale
        get() = localeProvider.getSystemLocale()

    val systemULocale: ULocale
        get() = localeProvider.getSystemULocale()

    fun setLocaleList(localeList: LocaleListCompat) {
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}
