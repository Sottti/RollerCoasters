package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppLanguageUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppLanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppLanguagePickerSelection

@Composable
internal fun LanguagePickerDialog(
    state: AppLanguagePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.appLanguages.map { language -> language.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            val selectedLanguage = selectedOption.toAppLanguageUi(state.appLanguages)
            onAction(AppLanguagePickerSelectionChange(selectedLanguage))
        },
        onConfirm = {
            onAction(ConfirmAppLanguagePickerSelection(state.appLanguages.findSelectedLanguage()))
        },
        onDismiss = { onAction(SettingsAction.DismissAppLanguagePicker) },
    )
}

private fun List<AppLanguageUi>.findSelectedLanguage(): AppLanguageUi =
    find { it.selected } ?: first()