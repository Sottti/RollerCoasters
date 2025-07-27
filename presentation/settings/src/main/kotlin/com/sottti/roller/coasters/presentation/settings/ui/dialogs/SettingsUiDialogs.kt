package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun SettingsDialogs(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    with(state) {
        appTheme.picker?.let { themePickerState ->
            AppThemePickerDialog(
                state = themePickerState,
                onAction = onAction,
            )
        }

        appColorContrast.picker?.let { colorContrastPickerState ->
            AppColorContrastPickerDialog(
                state = colorContrastPickerState,
                onAction = onAction,
            )
        }

        appColorContrast.notAvailableMessage?.let { colorContrastNotAvailableMessageState ->
            AppColorContrastNotAvailableDialog(
                state = colorContrastNotAvailableMessageState,
                onAction = onAction,
            )
        }

        appLanguage.picker?.let { languagePickerState ->
            AppLanguagePickerDialog(
                state = languagePickerState,
                onAction = onAction,
            )
        }

        appMeasurementSystem.picker?.let { measurementSystemPickerState ->
            AppMeasurementSystemPickerDialog(
                state = measurementSystemPickerState,
                onAction = onAction,
            )
        }
    }
}
