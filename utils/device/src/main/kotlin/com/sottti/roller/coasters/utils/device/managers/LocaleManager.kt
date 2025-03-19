package com.sottti.roller.coasters.utils.device.managers

import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale
import javax.inject.Inject

public class LocaleManager @Inject constructor(
    private val localeProvider: LocaleProvider,
) {
    public val appLocale: Locale
        get() = localeProvider.getAppLocale()

    public val systemLocale: Locale
        get() = localeProvider.getSystemLocale()

    public val systemULocale: ULocale
        get() = localeProvider.getSystemULocale()

    public fun setLocaleList(localeList: LocaleListCompat) {
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}