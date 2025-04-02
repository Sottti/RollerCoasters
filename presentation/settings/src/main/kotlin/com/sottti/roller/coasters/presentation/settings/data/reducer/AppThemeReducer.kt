package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.SystemAppTheme
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi.DarkTheme
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi.LightTheme
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi.SystemTheme
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateAppTheme(
    appTheme: AppTheme,
) {
    update { currentState ->
        currentState.copy(
            appTheme = currentState.appTheme.copy(
                currentState.appTheme.listItem.copy(
                    selectedAppTheme = SelectedAppThemeState.Loaded(
                        appTheme.toPresentationModel(selected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showAppThemePicker(
    selectedLightDarkAppThemingAvailable: Boolean,
    theme: AppThemeUi,
) {
    update { currentState ->
        currentState.copy(
            appTheme = currentState.appTheme.copy(
                picker = appThemePickerState(
                    lightDarkAppThemingAvailable = selectedLightDarkAppThemingAvailable,
                    theme = theme,
                )
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.updateAppThemePicker(
    selectedLightDarkAppThemingAvailable: Boolean,
    theme: AppThemeUi,
) {
    update { currentState ->
        currentState.copy(
            appTheme = currentState.appTheme.copy(
                picker = appThemePickerState(
                    lightDarkAppThemingAvailable = selectedLightDarkAppThemingAvailable,
                    theme = theme,
                )
            ),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideAppThemePicker() {
    update { currentState ->
        currentState.copy(appTheme = currentState.appTheme.copy(picker = null))
    }
}

private fun appThemePickerState(
    lightDarkAppThemingAvailable: Boolean,
    theme: AppThemeUi,
) = ThemePickerState(
    title = R.string.theme_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    appThemes = appThemesList(lightDarkAppThemingAvailable, theme),
)

private fun appThemesList(
    lightDarkAppThemingAvailable: Boolean,
    selectedAppTheme: AppThemeUi,
) = listOfNotNull(
    SystemAppTheme.toPresentationModel(selected = selectedAppTheme is SystemTheme)
        .takeIf { lightDarkAppThemingAvailable },
    LightAppTheme.toPresentationModel(selected = selectedAppTheme is LightTheme),
    DarkAppTheme.toPresentationModel(selected = selectedAppTheme is DarkTheme),
)