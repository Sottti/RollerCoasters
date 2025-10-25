package com.sotti.roller.coasters.presentation.settings.data.reducer

import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme.System
import com.sotti.roller.coasters.presentation.settings.R
import com.sotti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sotti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sotti.roller.coasters.presentation.settings.model.DarkTheme
import com.sotti.roller.coasters.presentation.settings.model.LightTheme
import com.sotti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sotti.roller.coasters.presentation.settings.model.SettingsState
import com.sotti.roller.coasters.presentation.settings.model.SystemTheme
import com.sotti.roller.coasters.presentation.settings.model.ThemePickerState

internal fun SettingsState.updateAppTheme(
    newAppTheme: AppTheme,
): SettingsState = copy(
    appTheme = appTheme.copy(
        listItem = appTheme.listItem.copy(
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
): SettingsState = copy(
    appTheme = appTheme.copy(
        picker = appThemePickerState(
            lightDarkAppThemingAvailable = true,
            selectedAppTheme = selectedAppTheme,
        )
    )
)

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
