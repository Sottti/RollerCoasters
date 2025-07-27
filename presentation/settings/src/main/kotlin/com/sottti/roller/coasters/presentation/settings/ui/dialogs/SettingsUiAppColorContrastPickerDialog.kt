package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.AppColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastPicker

private const val COLOR_CONTRAST_EMPTY_ERROR = "Color contrast list must not be empty"

@Composable
internal fun AppColorContrastPickerDialog(
    state: AppColorContrastPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    GenericPickerDialog(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        items = state.appColorContrasts,
        toOption = { appColorContrast -> appColorContrast.toRadioButtonOption() },
        fromOption = { option, list -> option.toAppColorContrastUi(list) },
        findSelected = { appColorContrasts ->
            appColorContrasts.firstSelectedOrFirst(
                isSelected = { appColorContrast -> appColorContrast.selected },
                errorMessage = COLOR_CONTRAST_EMPTY_ERROR,
            )
        },
        onSelect = { onAction(AppColorContrastPickerSelectionChange(it)) },
        onConfirm = { onAction(ConfirmColorContrastPickerSelection(it)) },
        onDismiss = { onAction(DismissAppColorContrastPicker) },
    )
}
