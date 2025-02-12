package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.presentation.design.system.dialogs.DialogRadioButtonOption
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

internal fun Language.toPresentationModel(isSelected: Boolean): LanguageUi =
    when (this) {
        Language.EnglishGbLanguage -> EnglishGbLanguage(
            text = R.string.language_english_gb,
            icon = Icons.LanguageEnglishGb.Rounded,
            selected = isSelected,
        )

        Language.SpanishSpainLanguage -> SpanishSpainLanguage(
            text = R.string.language_spanish_spain,
            icon = Icons.LanguageSpanish.Rounded,
            selected = isSelected,
        )

        Language.GalicianLanguage -> GalicianLanguage(
            text = R.string.language_galician,
            icon = Icons.Language.Rounded,
            selected = isSelected,
        )

        Language.SystemLanguage -> SystemLanguage(
            text = R.string.language_system,
            icon = if (isSelected) Icons.Smartphone.Filled else Icons.Smartphone.Outlined,
            selected = isSelected,
        )
    }

internal fun LanguageUi.toDomain(): Language =
    when (this) {
        is EnglishGbLanguage -> Language.EnglishGbLanguage
        is SpanishSpainLanguage -> Language.SpanishSpainLanguage
        is GalicianLanguage -> Language.GalicianLanguage
        is SystemLanguage -> Language.SystemLanguage
    }