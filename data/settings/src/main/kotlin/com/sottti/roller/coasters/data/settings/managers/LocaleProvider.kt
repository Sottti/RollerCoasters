package com.sottti.roller.coasters.data.settings.managers

import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale
import javax.inject.Inject

internal class LocaleProvider @Inject constructor() {
    fun getAppLocale(): Locale =
        AppCompatDelegate.getApplicationLocales().get(0) ?: getSystemLocale()

    fun getSystemLocale(): Locale = Locale.getDefault()

    fun getSystemULocale(): ULocale = ULocale.getDefault()
}