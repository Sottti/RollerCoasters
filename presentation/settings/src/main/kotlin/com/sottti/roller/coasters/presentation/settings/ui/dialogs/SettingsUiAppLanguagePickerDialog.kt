package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppLanguageUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppLanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.AppLanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.ConfirmAppLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.DismissAppLanguagePicker

private const val LANGUAGE_EMPTY_ERROR = "Language list must not be empty"

@Composable
internal fun AppLanguagePickerDialog(
    state: AppLanguagePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    GenericPickerDialog(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        items = state.appLanguages,
        toOption = { appLanguage -> appLanguage.toRadioButtonOption() },
        fromOption = { option, list -> option.toAppLanguageUi(list) },
        findSelected = { appLanguages ->
            appLanguages.firstSelectedOrFirst(
                isSelected = { appLanguage -> appLanguage.selected },
                errorMessage = LANGUAGE_EMPTY_ERROR,
            )
        },
        onSelect = { onAction(AppLanguagePickerSelectionChange(it)) },
        onConfirm = { onAction(ConfirmAppLanguagePickerSelection(it)) },
        onDismiss = { onAction(DismissAppLanguagePicker) },
    )
}
