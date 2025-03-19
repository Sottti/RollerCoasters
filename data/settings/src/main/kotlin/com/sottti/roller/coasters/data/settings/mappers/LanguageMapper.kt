package com.sottti.roller.coasters.data.settings.mappers

import androidx.annotation.VisibleForTesting
import androidx.core.os.LocaleListCompat
import androidx.core.os.LocaleListCompat.forLanguageTags
import androidx.core.os.LocaleListCompat.getEmptyLocaleList
import com.sottti.roller.coasters.domain.settings.model.language.Language
import java.util.Locale

@VisibleForTesting
internal const val LOCALE_GALICIA_TAG = "gl-ES"

@VisibleForTesting
internal const val LOCALE_SPAIN_TAG = "es-ES"

@VisibleForTesting
internal const val LOCALE_UK_TAG = "en-GB"

internal fun Language.toLocaleList(): LocaleListCompat = when (this) {
    Language.EnglishGbLanguage -> forLanguageTags(LOCALE_UK_TAG)
    Language.GalicianLanguage -> forLanguageTags("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
    Language.SpanishSpainLanguage -> forLanguageTags(LOCALE_SPAIN_TAG)
    Language.SystemLanguage -> getEmptyLocaleList()
}

internal fun Locale?.toLanguage(): Language = when (this?.toLanguageTag()) {
    LOCALE_UK_TAG -> Language.EnglishGbLanguage
    LOCALE_GALICIA_TAG -> Language.GalicianLanguage
    LOCALE_SPAIN_TAG -> Language.SpanishSpainLanguage
    else -> Language.SystemLanguage
}