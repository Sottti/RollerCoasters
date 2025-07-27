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
