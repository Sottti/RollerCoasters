package com.sottti.roller.coasters.utils.device.managers

import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale
import javax.inject.Inject

public class LocaleProvider @Inject constructor() {
    public fun getAppLocale(): Locale =
        AppCompatDelegate.getApplicationLocales().get(0) ?: getDefaultLocale()

    public fun getDefaultLocale(): Locale = Locale.getDefault()
}