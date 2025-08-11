package com.sottti.roller.coasters.presentation.format.mapper

import com.sottti.roller.coasters.domain.locales.localeEs
import com.sottti.roller.coasters.domain.locales.localeGb
import com.sottti.roller.coasters.domain.locales.localeGl
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import java.util.Locale

internal fun AppLanguage.toLocale(systemLocale: Locale): Locale =
    when (this) {
        AppLanguage.EnglishGb -> localeGb
        AppLanguage.Galician -> localeGl
        AppLanguage.SpanishSpain -> localeEs
        AppLanguage.System -> systemLocale
    }