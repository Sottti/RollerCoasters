package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.System
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sottti.roller.coasters.presentation.settings.model.DarkTheme
import com.sottti.roller.coasters.presentation.settings.model.LightTheme
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.SystemTheme
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState

internal fun SettingsState.updateAppTheme(
    newAppTheme: AppTheme,
): SettingsState = copy(
    appTheme = appTheme.copy(
        appTheme.listItem.copy(
            selectedAppTheme = SelectedAppThemeState.Loaded(
                newAppTheme.toPresentationModel(selected = true),
            )
        ),
    ),
)

internal fun SettingsState.showAppThemePicker(
    lightDarkAppThemingAvailable: Boolean,
    selectedAppTheme: AppThemeUi,
): SettingsState = copy(
    appTheme = appTheme.copy(
        picker = appThemePickerState(
            lightDarkAppThemingAvailable = lightDarkAppThemingAvailable,
            selectedAppTheme = selectedAppTheme,
        )
    )
)

internal fun SettingsState.updateAppThemePicker(
    selectedAppTheme: AppThemeUi,
): SettingsState {
    val currentPicker = appTheme.picker ?: return this
    val currentThemes = currentPicker.appThemes

    val updatedThemes = currentThemes.map { theme ->
        val shouldBeSelected = theme::class == selectedAppTheme::class
        when (theme.selected) {
            shouldBeSelected -> theme
            else -> theme.withSelected(shouldBeSelected)
        }
    }

    if (updatedThemes === currentThemes || updatedThemes == currentThemes) return this

    return copy(
        appTheme = appTheme.copy(
            picker = currentPicker.copy(appThemes = updatedThemes),
        ),
    )
}

internal fun SettingsState.hideAppThemePicker(): SettingsState =
    copy(appTheme = appTheme.copy(picker = null))

private fun appThemePickerState(
    lightDarkAppThemingAvailable: Boolean,
    selectedAppTheme: AppThemeUi,
) = ThemePickerState(
    title = R.string.theme_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    appThemes = appThemesList(lightDarkAppThemingAvailable, selectedAppTheme),
)

private fun appThemesList(
    lightDarkAppThemingAvailable: Boolean,
    selectedAppTheme: AppThemeUi,
) = listOfNotNull(
    System.toPresentationModel(selected = selectedAppTheme is SystemTheme)
        .takeIf { lightDarkAppThemingAvailable },
    LightAppTheme.toPresentationModel(selected = selectedAppTheme is LightTheme),
    DarkAppTheme.toPresentationModel(selected = selectedAppTheme is DarkTheme),
)

private fun AppThemeUi.withSelected(selected: Boolean): AppThemeUi = when (this) {
    is DarkTheme -> copy(selected = selected)
    is LightTheme -> copy(selected = selected)
    is SystemTheme -> copy(selected = selected)
}