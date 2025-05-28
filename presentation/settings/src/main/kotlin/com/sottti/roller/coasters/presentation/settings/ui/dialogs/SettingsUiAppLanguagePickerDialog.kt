package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppLanguageUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppLanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.AppLanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.ConfirmAppLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.DismissAppLanguagePicker

@Composable
internal fun LanguagePickerDialog(
    state: AppLanguagePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    val options = remember(state.appLanguages) {
        state.appLanguages.map { language -> language.toRadioButtonOption() }
    }
    val onOptionSelected = remember(onAction, state.appLanguages) {
        { selectedOption: DialogRadioButtonOption ->
            val selectedLanguage = selectedOption.toAppLanguageUi(state.appLanguages)
            onAction(AppLanguagePickerSelectionChange(selectedLanguage))
        }
    }
    val onConfirm = remember(onAction, state.appLanguages) {
        { onAction(ConfirmAppLanguagePickerSelection(state.appLanguages.findSelectedLanguage())) }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppLanguagePicker) } }
    DialogWithRadioButtons(
        confirm = state.confirm,
        dismiss = state.dismiss,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        onOptionSelected = onOptionSelected,
        options = options,
        title = state.title,
    )
}

private fun List<AppLanguageUi>.findSelectedLanguage(): AppLanguageUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("app languages list should not be empty")