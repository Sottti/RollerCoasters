package com.sottti.roller.coasters.data.settings.mapper

import androidx.core.os.LocaleListCompat
import androidx.core.os.LocaleListCompat.forLanguageTags
import androidx.core.os.LocaleListCompat.getEmptyLocaleList
import com.sottti.roller.coasters.domain.locales.localeEs
import com.sottti.roller.coasters.domain.locales.localeGb
import com.sottti.roller.coasters.domain.locales.localeGl
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import java.util.Locale

internal fun AppLanguage.toLocaleList(): LocaleListCompat = when (this) {
    AppLanguage.EnglishGb -> forLanguageTags(localeGb.toLanguageTag())
    AppLanguage.Galician -> forLanguageTags("${localeGl.toLanguageTag()},${localeEs.toLanguageTag()}")
    AppLanguage.SpanishSpain -> forLanguageTags(localeEs.toLanguageTag())
    AppLanguage.System -> getEmptyLocaleList()
}

internal fun Locale?.toLanguage(): AppLanguage = when (this?.toLanguageTag()) {
    localeGb.toLanguageTag() -> AppLanguage.EnglishGb
    localeGl.toLanguageTag() -> AppLanguage.Galician
    localeEs.toLanguageTag() -> AppLanguage.SpanishSpain
    else -> AppLanguage.System
}