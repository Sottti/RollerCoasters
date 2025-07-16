package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppThemeUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.AppThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState

private const val THEME_EMPTY_ERROR = "Theme list must not be empty"

@Composable
internal fun AppThemePickerDialog(
    state: ThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    GenericPickerDialog(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        items = state.appThemes,
        toOption = { appTheme -> appTheme.toRadioButtonOption() },
        fromOption = { option, list -> option.toAppThemeUi(list) },
        findSelected = { appThemes ->
            appThemes.firstSelectedOrFirst(
                isSelected = { appTheme -> appTheme.selected },
                errorMessage = THEME_EMPTY_ERROR,
            )
        },
        onSelect = { onAction(AppThemePickerSelectionChange(it)) },
        onConfirm = { onAction(ConfirmAppThemePickerSelection(it)) },
        onDismiss = { onAction(DismissAppThemePicker) },
    )
}