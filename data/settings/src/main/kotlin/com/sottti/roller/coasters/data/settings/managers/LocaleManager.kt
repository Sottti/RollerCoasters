package com.sottti.roller.coasters.data.settings.managers

import android.content.Context
import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.sottti.roller.coasters.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.util.Locale
import javax.inject.Inject

internal class LocaleManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val localeProvider: LocaleProvider,
) {
    val appLocale: Locale
        get() = localeProvider.getAppLocale()

    fun observeAppLocale() : Flow<Locale> =
        context.observeConfigurationChanges { appLocale }

    val systemLocale: Locale
        get() = localeProvider.getSystemLocale()

    fun observeSystemLocale() : Flow<Locale> =
        context.observeConfigurationChanges { systemLocale }

    val systemULocale: ULocale
        get() = localeProvider.getSystemULocale()

    fun setLocaleList(localeList: LocaleListCompat) {
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}
