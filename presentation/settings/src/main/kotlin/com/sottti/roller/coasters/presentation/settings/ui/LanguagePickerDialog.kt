package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.data.mapper.toLanguageUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.LanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LanguagePickerSelectionChange

@Composable
internal fun LanguagePickerDialog(
    state: LanguagePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    Dialog.WithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.languages.map { language -> language.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            val selectedLanguage = selectedOption.toLanguageUi(state.languages)
            onAction(LanguagePickerSelectionChange(selectedLanguage))
        },
        onConfirm = {
            onAction(ConfirmLanguagePickerSelection(state.languages.findSelectedLanguage()))
        },
        onDismiss = { onAction(SettingsAction.DismissLanguagePicker) },
    )
}

private fun List<LanguageUi>.findSelectedLanguage(): LanguageUi =
    find { it.selected } ?: first()