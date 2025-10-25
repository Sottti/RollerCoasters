package com.sotti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sotti.roller.coasters.presentation.settings.data.mapper.toAppThemeUi
import com.sotti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppThemePickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppThemePickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppThemePicker
import com.sotti.roller.coasters.presentation.settings.model.ThemePickerState

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
