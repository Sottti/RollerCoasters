package com.sottti.roller.coasters.data.settings.mapper

import androidx.annotation.VisibleForTesting
import androidx.core.os.LocaleListCompat
import androidx.core.os.LocaleListCompat.forLanguageTags
import androidx.core.os.LocaleListCompat.getEmptyLocaleList
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import java.util.Locale

@VisibleForTesting
internal const val LOCALE_GALICIA_TAG = "gl-ES"

@VisibleForTesting
internal const val LOCALE_SPAIN_TAG = "es-ES"

@VisibleForTesting
internal const val LOCALE_UK_TAG = "en-GB"

@VisibleForTesting
internal const val ENGLISH_GB_KEY = "english_gb_language"

@VisibleForTesting
internal const val GALICIAN_KEY = "galician_language"

@VisibleForTesting
internal const val SPANISH_SPAIN_KEY = "spanish_spain_language"

@VisibleForTesting
internal const val SYSTEM_KEY = "system_language"

internal val AppLanguage.key: String
    get() = when (this) {
        AppLanguage.EnglishGb -> ENGLISH_GB_KEY
        AppLanguage.Galician -> GALICIAN_KEY
        AppLanguage.SpanishSpain -> SPANISH_SPAIN_KEY
        AppLanguage.System -> SYSTEM_KEY
    }

internal fun String.toAppLanguage(): AppLanguage =
    when (this) {
        ENGLISH_GB_KEY -> AppLanguage.EnglishGb
        GALICIAN_KEY -> AppLanguage.Galician
        SPANISH_SPAIN_KEY -> AppLanguage.SpanishSpain
        SYSTEM_KEY -> AppLanguage.System
        else -> AppLanguage.System
    }

internal fun AppLanguage.toLocaleList(): LocaleListCompat = when (this) {
    AppLanguage.EnglishGb -> forLanguageTags(LOCALE_UK_TAG)
    AppLanguage.Galician -> forLanguageTags("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
    AppLanguage.SpanishSpain -> forLanguageTags(LOCALE_SPAIN_TAG)
    AppLanguage.System -> getEmptyLocaleList()
}

internal fun Locale?.toLanguage(): AppLanguage = when (this?.toLanguageTag()) {
    LOCALE_UK_TAG -> AppLanguage.EnglishGb
    LOCALE_GALICIA_TAG -> AppLanguage.Galician
    LOCALE_SPAIN_TAG -> AppLanguage.SpanishSpain
    else -> AppLanguage.System
}