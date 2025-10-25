package com.sotti.roller.coasters.presentation.format.mapper

import com.sotti.roller.coasters.domain.locales.localeEs
import com.sotti.roller.coasters.domain.locales.localeGb
import com.sotti.roller.coasters.domain.locales.localeGl
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import java.util.Locale

internal fun AppLanguage.toLocale(systemLocale: Locale): Locale =
    when (this) {
        AppLanguage.EnglishGb -> localeGb
        AppLanguage.Galician -> localeGl
        AppLanguage.SpanishSpain -> localeEs
        AppLanguage.System -> systemLocale
    }
