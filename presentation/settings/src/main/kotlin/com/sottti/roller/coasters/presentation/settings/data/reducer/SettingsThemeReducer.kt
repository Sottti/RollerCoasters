package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.domain.model.Theme.DarkTheme
import com.sottti.roller.coasters.domain.model.Theme.LightTheme
import com.sottti.roller.coasters.domain.model.Theme.SystemTheme
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateTheme(
    theme: Theme,
) {
    update { currentState ->
        currentState.copy(
            theme = currentState.theme.copy(
                currentState.theme.listItem.copy(
                    selectedTheme = SelectedThemeState.Loaded(
                        theme.toPresentationModel(isSelected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showThemePicker(
    lightDarkThemeThemingAvailable: Boolean,
    theme: ThemeUi,
) {
    update { currentState ->
        currentState.copy(
            theme = currentState.theme.copy(
                picker = themePickerState(
                    lightDarkThemeThemingAvailable = lightDarkThemeThemingAvailable,
                    theme = theme,
                )
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.updateThemePicker(
    lightDarkThemeThemingAvailable: Boolean,
    theme: ThemeUi,
) {
    update { currentState ->
        currentState.copy(
            theme = currentState.theme.copy(
                picker = themePickerState(
                    lightDarkThemeThemingAvailable = lightDarkThemeThemingAvailable,
                    theme = theme
                )
            ),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideThemePicker() {
    update { currentState ->
        currentState.copy(theme = currentState.theme.copy(picker = null))
    }
}

private fun themePickerState(
    lightDarkThemeThemingAvailable: Boolean,
    theme: ThemeUi,
) = ThemePickerState(
    title = R.string.theme_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    themes = themesList(lightDarkThemeThemingAvailable, theme),
)

private fun themesList(
    lightDarkThemeThemingAvailable: Boolean,
    selectedTheme: ThemeUi,
) = listOfNotNull(
    SystemTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.SystemTheme)
        .takeIf { lightDarkThemeThemingAvailable },
    LightTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.LightTheme),
    DarkTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.DarkTheme),
)