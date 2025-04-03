package com.sottti.roller.coasters.presentation.format.mapper

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import java.util.Locale

@VisibleForTesting
internal const val LOCALE_GALICIA_TAG = "gl-ES"

@VisibleForTesting
internal const val LOCALE_SPAIN_TAG = "es-ES"

@VisibleForTesting
internal const val LOCALE_UK_TAG = "en-GB"

internal fun AppLanguage.toLocale(systemLocale: Locale): Locale = when (this) {
    AppLanguage.EnglishGb -> Locale.forLanguageTag(LOCALE_UK_TAG)
    AppLanguage.Galician -> Locale.forLanguageTag(LOCALE_GALICIA_TAG)
    AppLanguage.SpanishSpain -> Locale.forLanguageTag(LOCALE_SPAIN_TAG)
    AppLanguage.System -> systemLocale
}