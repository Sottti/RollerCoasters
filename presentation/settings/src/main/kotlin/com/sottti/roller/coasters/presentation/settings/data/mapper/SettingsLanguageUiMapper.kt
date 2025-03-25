package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.EnglishGbLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.GalicianLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.SpanishSpainLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.SystemLanguage

@Composable
internal fun LanguageUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toLanguageUi(
    languages: List<LanguageUi>,
): LanguageUi = languages.find { it.text == text } ?: languages.first()

internal fun AppLanguage.toPresentationModel(isSelected: Boolean): LanguageUi =
    when (this) {
        AppLanguage.EnglishGb -> EnglishGbLanguage(
            text = R.string.language_english_gb,
            icon = Icons.LanguageEnglishGb.Outlined,
            selected = isSelected,
        )

        AppLanguage.SpanishSpain -> SpanishSpainLanguage(
            text = R.string.language_spanish_spain,
            icon = Icons.LanguageSpanish.Outlined,
            selected = isSelected,
        )

        AppLanguage.Galician -> GalicianLanguage(
            text = R.string.language_galician,
            icon = Icons.Language.Outlined,
            selected = isSelected,
        )

        AppLanguage.System -> SystemLanguage(
            text = R.string.language_system,
            icon = if (isSelected) Icons.Smartphone.Filled else Icons.Smartphone.Outlined,
            selected = isSelected,
        )
    }

internal fun LanguageUi.toDomain(): AppLanguage =
    when (this) {
        is EnglishGbLanguage -> AppLanguage.EnglishGb
        is SpanishSpainLanguage -> AppLanguage.SpanishSpain
        is GalicianLanguage -> AppLanguage.Galician
        is SystemLanguage -> AppLanguage.System
    }