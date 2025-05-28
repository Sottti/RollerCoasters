package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

@Composable
internal fun Dialogs(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    state.appTheme.picker?.let { themePickerState ->
        AppThemePickerDialog(
            state = themePickerState,
            onAction = onAction,
        )
    }

    state.appColorContrast.picker?.let { colorContrastPickerState ->
        AppColorContrastPickerDialog(
            state = colorContrastPickerState,
            onAction = onAction,
        )
    }

    state.appColorContrast.notAvailableMessage?.let { colorContrastNotAvailableMessageState ->
        AppColorContrastNotAvailableDialog(
            state = colorContrastNotAvailableMessageState,
            onAction = onAction,
        )
    }

    state.appLanguage.picker?.let { languagePickerState ->
        LanguagePickerDialog(
            state = languagePickerState,
            onAction = onAction,
        )
    }

    state.appMeasurementSystem.picker?.let { measurementSystemPickerState ->
        AppMeasurementSystemPickerDialog(
            state = measurementSystemPickerState,
            onAction = onAction,
        )
    }
}