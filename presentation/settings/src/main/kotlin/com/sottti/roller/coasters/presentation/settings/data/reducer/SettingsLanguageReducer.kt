package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.model.Language.EnglishGbLanguage
import com.sottti.roller.coasters.domain.model.Language.GalicianLanguage
import com.sottti.roller.coasters.domain.model.Language.SpanishSpainLanguage
import com.sottti.roller.coasters.domain.model.Language.SystemLanguage
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.LanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi
import com.sottti.roller.coasters.presentation.settings.model.SelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateLanguage(
    language: Language,
) {
    update { currentState ->
        currentState.copy(
            language = currentState.language.copy(
                listItem = currentState.language.listItem.copy(
                    selectedLanguage = SelectedLanguageState.Loaded(
                        language.toPresentationModel(isSelected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showLanguagePicker(
    language: Language,
) {
    update { currentState ->
        currentState.copy(
            language = currentState.language.copy(
                picker = languagePickerState(language.toPresentationModel(isSelected = true)),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.updateLanguagePicker(
    selectedLanguage: LanguageUi,
) {
    update { currentState ->
        currentState.copy(
            language = currentState
                .language
                .copy(picker = languagePickerState(selectedLanguage)),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideLanguagePicker() {
    update { currentState ->
        currentState.copy(
            language = currentState.language.copy(picker = null)
        )
    }
}

private fun languagePickerState(
    selectedLanguage: LanguageUi,
) = LanguagePickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    languages = languagesList(selectedLanguage),
)

private fun languagesList(
    selectedLanguage: LanguageUi,
) = listOfNotNull(
    SystemLanguage.toPresentationModel(isSelected = selectedLanguage is LanguageUi.SystemLanguage),
    EnglishGbLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.EnglishGbLanguage,
    ),
    SpanishSpainLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.SpanishSpainLanguage,
    ),
    GalicianLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.GalicianLanguage,
    ),
)