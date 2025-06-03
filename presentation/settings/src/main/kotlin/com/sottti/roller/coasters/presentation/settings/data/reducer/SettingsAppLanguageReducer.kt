package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.EnglishGb
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.Galician
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.SpanishSpain
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.System
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppLanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageUi
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.EnglishGbLanguage
import com.sottti.roller.coasters.presentation.settings.model.GalicianLanguage
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.SpanishSpainLanguage
import com.sottti.roller.coasters.presentation.settings.model.SystemLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateAppLanguage(
    appLanguage: AppLanguage,
) {
    update { currentState ->
        currentState.copy(
            appLanguage = currentState.appLanguage.copy(
                listItem = currentState.appLanguage.listItem.copy(
                    selectedAppLanguage = AppSelectedLanguageState.Loaded(
                        appLanguage.toPresentationModel(selected = true),
                    ),
                ),
            ),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showAppLanguagePicker(
    appLanguage: AppLanguage,
) {
    update { currentState ->
        currentState.copy(
            appLanguage = currentState.appLanguage.copy(
                picker = appLanguagePickerState(appLanguage.toPresentationModel(selected = true)),
            ),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.updateAppLanguagePicker(
    selectedAppLanguage: AppLanguageUi,
) {
    update { currentState ->
        currentState.copy(
            appLanguage = currentState
                .appLanguage
                .copy(picker = appLanguagePickerState(selectedAppLanguage)),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideAppLanguagePicker() {
    update { currentState ->
        currentState.copy(
            appLanguage = currentState.appLanguage.copy(picker = null),
        )
    }
}

private fun appLanguagePickerState(
    selectedAppLanguage: AppLanguageUi,
) = AppLanguagePickerState(
    title = R.string.language_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    appLanguages = appLanguagesList(selectedAppLanguage),
)

private fun appLanguagesList(
    selectedLanguage: AppLanguageUi,
) = listOf(
    System.toPresentationModel(selected = selectedLanguage is SystemLanguage),
    EnglishGb.toPresentationModel(selected = selectedLanguage is EnglishGbLanguage),
    SpanishSpain.toPresentationModel(selected = selectedLanguage is SpanishSpainLanguage),
    Galician.toPresentationModel(selected = selectedLanguage is GalicianLanguage),
)