package com.sottti.roller.coasters.presentation.settings.data.mapper

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageUi
import com.sottti.roller.coasters.presentation.settings.model.EnglishGbLanguage
import com.sottti.roller.coasters.presentation.settings.model.GalicianLanguage
import com.sottti.roller.coasters.presentation.settings.model.SpanishSpainLanguage
import com.sottti.roller.coasters.presentation.settings.model.SystemLanguage

internal fun AppLanguageUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppLanguageUi(
    languages: List<AppLanguageUi>,
): AppLanguageUi = languages.find { it.text == text } ?: languages.first()

internal fun AppLanguage.toPresentationModel(selected: Boolean): AppLanguageUi =
    when (this) {
        AppLanguage.EnglishGb -> englishGbLanguage(selected)
        AppLanguage.SpanishSpain -> spanishSpainLanguage(selected)
        AppLanguage.Galician -> galicianLanguage(selected)
        AppLanguage.System -> systemLanguage(selected)
    }

internal fun AppLanguageUi.toDomain(): AppLanguage =
    when (this) {
        is EnglishGbLanguage -> AppLanguage.EnglishGb
        is SpanishSpainLanguage -> AppLanguage.SpanishSpain
        is GalicianLanguage -> AppLanguage.Galician
        is SystemLanguage -> AppLanguage.System
    }

private fun systemLanguage(
    selected: Boolean,
): SystemLanguage =
    SystemLanguage(
        text = R.string.language_system,
        icon = if (selected) Icons.Smartphone.filled else Icons.Smartphone.outlined,
        selected = selected,
    )

private fun galicianLanguage(
    selected: Boolean,
): GalicianLanguage =
    GalicianLanguage(
        text = R.string.language_galician,
        icon = Icons.Language.outlined,
        selected = selected,
    )

private fun spanishSpainLanguage(
    selected: Boolean,
): SpanishSpainLanguage =
    SpanishSpainLanguage(
        text = R.string.language_spanish_spain,
        icon = Icons.LanguageSpanish.outlined,
        selected = selected,
    )

private fun englishGbLanguage(
    selected: Boolean,
): EnglishGbLanguage = EnglishGbLanguage(
    text = R.string.language_english_gb,
    icon = Icons.LanguageEnglishGb.outlined,
    selected = selected,
)
