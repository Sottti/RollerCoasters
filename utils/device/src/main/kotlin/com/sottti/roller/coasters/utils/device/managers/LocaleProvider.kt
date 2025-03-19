package com.sottti.roller.coasters.utils.device.managers

import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale
import javax.inject.Inject

public class LocaleProvider @Inject constructor() {
    public fun getAppLocale(): Locale =
        AppCompatDelegate.getApplicationLocales().get(0) ?: getSystemLocale()

    public fun getSystemLocale(): Locale = Locale.getDefault()

    public fun getSystemULocale(): ULocale = ULocale.getDefault()
}