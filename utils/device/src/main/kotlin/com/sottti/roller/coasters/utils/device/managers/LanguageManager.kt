package com.sottti.roller.coasters.utils.device.managers

import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.utils.device.mappers.toLanguage
import com.sottti.roller.coasters.utils.device.mappers.toLocaleList
import java.util.Locale
import javax.inject.Inject

public class LanguageManager @Inject constructor(
    private val localeProvider: LocaleProvider,
) {
    public val locale: Locale
        get() = localeProvider.getAppLocale()

    public val language: Language
        get() = locale.toLanguage()

    public fun setLanguage(language: Language) {
        AppCompatDelegate.setApplicationLocales(language.toLocaleList())
    }
}