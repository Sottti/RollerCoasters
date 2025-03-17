package com.sottti.roller.coasters.utils.device.system

import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale
import javax.inject.Inject

internal class AndroidLocaleProvider @Inject constructor() : LocaleProvider {
    override fun getAppLocale(): Locale? =
        AppCompatDelegate.getApplicationLocales().get(0)

    override fun getDefaultLocale(): Locale = Locale.getDefault()
}