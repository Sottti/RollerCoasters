package com.sottti.roller.coasters.presentation.settings.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.domain.settings.model.Theme.DarkTheme
import com.sottti.roller.coasters.domain.settings.model.Theme.LightTheme
import com.sottti.roller.coasters.domain.settings.model.Theme.SystemTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppTheme
import com.sottti.roller.coasters.presentation.settings.model.AppThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.AppThemeState
import com.sottti.roller.coasters.presentation.settings.model.CurrentThemeState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.*
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.TopBarState
import com.sottti.roller.coasters.utils.device.isDynamicColorEnabled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()
    val onAction: (SettingsAction) -> Unit = { action -> processAction(action) }

    init {
        if (isDynamicColorEnabled()) observeDynamicColor()
        observeAppTheme()
    }

    private fun observeDynamicColor() {
        viewModelScope.launch {
            settingsRepository
                .observeDynamicColor()
                .collect { dynamicColorChecked -> updateDynamicColor(dynamicColorChecked) }
        }
    }

    private fun updateDynamicColor(dynamicColorChecked: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                dynamicColor = currentState
                    .dynamicColor
                    ?.copy(checkedState = DynamicColorCheckedState.Loaded(dynamicColorChecked))
            )
        }
    }

    private fun observeAppTheme() {
        viewModelScope.launch {
            settingsRepository
                .observeAppTheme()
                .collect { theme -> updateAppTheme(theme) }
        }
    }

    private fun updateAppTheme(theme: Theme) {
        _state.update { currentTheme ->
            currentTheme.copy(
                appTheme = currentTheme.appTheme.copy(
                    selectedTheme = CurrentThemeState.Loaded(
                        theme.toPresentationModel(currentTheme.appTheme.selectedTheme == theme),
                    )
                ),
            )
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> setDynamicColor(action.checked)
                LaunchAppThemePicker -> showAppThemePicker()
                is AppThemePickerSelectionChange -> updateAppThemePicker(action.theme)
                is ConfirmAppThemePickerSelection -> setAppTheme(action.theme)
                DismissAppThemePicker -> hideAppThemePicker()
            }
        }
    }

    private suspend fun showAppThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                appThemePicker = appThemePickerState(
                    selectedTheme = settingsRepository
                        .getAppTheme()
                        .toPresentationModel(isSelectedTheme = true)
                )
            )
        }
    }

    private fun updateAppThemePicker(
        selectedTheme: AppTheme,
    ) {
        _state.update { currentState ->
            currentState.copy(
                appThemePicker = appThemePickerState(selectedTheme = selectedTheme)
            )
        }
    }

    private fun hideAppThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                appThemePicker = null,
            )
        }
    }

    private suspend fun setDynamicColor(enabled: Boolean) {
        settingsRepository.setDynamicColor(enabled)
    }

    private suspend fun setAppTheme(theme: AppTheme) {
        hideAppThemePicker()
        settingsRepository.setAppTheme(theme.toDomainModel())
    }
}

private fun initialState(): SettingsState =
    SettingsState(
        dynamicColor = dynamicColorInitialState().takeIf { isDynamicColorEnabled() },
        appTheme = appThemeInitialState(),
        appThemePicker = null,
        topBar = topBarState(),
    )

private fun dynamicColorInitialState() = DynamicColorState(
    checkedState = DynamicColorCheckedState.Loading,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.Outlined
)

private fun appThemeInitialState() = AppThemeState(
    headline = R.string.theme_color_headline,
    supporting = R.string.theme_color_supporting,
    selectedTheme = CurrentThemeState.Loading,
    icon = Icons.Colors.Rounded
)

private fun topBarState(): TopBarState =
    TopBarState(title = R.string.title, icon = Icons.ArrowBack.Rounded)

private fun appThemePickerState(
    selectedTheme: AppTheme,
) = AppThemePickerState(
    title = R.string.theme_color_picker_title,
    confirm = R.string.theme_color_picker_confirm,
    dismiss = R.string.theme_color_picker_dismiss,
    themes = themesList(selectedTheme),
)

private fun themesList(
    selectedTheme: AppTheme,
) = listOf(
    SystemTheme.toPresentationModel(isSelectedTheme = selectedTheme is AppTheme.SystemTheme),
    LightTheme.toPresentationModel(isSelectedTheme = selectedTheme is AppTheme.LightTheme),
    DarkTheme.toPresentationModel(isSelectedTheme = selectedTheme is AppTheme.DarkTheme),
)

