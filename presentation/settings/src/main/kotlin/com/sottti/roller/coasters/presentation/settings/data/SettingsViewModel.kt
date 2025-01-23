package com.sottti.roller.coasters.presentation.settings.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemeSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.ThemeState
import com.sottti.roller.coasters.presentation.settings.model.ThemeWithText
import com.sottti.roller.coasters.presentation.settings.model.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()
    val onAction: (SettingsAction) -> Unit = { action -> processAction(action) }

    init {
        viewModelScope.launch {
            settingsRepository.observeDynamicColor().collect { dynamicColorChecked ->
                _state.update { currentState ->
                    currentState.copy(
                        dynamicColor = currentState.dynamicColor.copy(checked = dynamicColorChecked)
                    )
                }
            }
        }

        viewModelScope.launch {
            settingsRepository.observeAppTheme().collect { theme ->
                _state.update { currentTheme ->
                    currentTheme.copy(
                        theme = themeState(theme.toPresentationModel()),
                    )
                }
            }
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                LaunchThemePicker -> showThemePicker()
                DismissThemePicker -> hideThemePicker()
                is DynamicColorCheckedChange -> setDynamicColor(action.checked)
                is ConfirmThemeSelection -> setAppTheme(action.theme)
            }
        }
    }

    private suspend fun showThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                themePicker = themePickerState(
                    settingsRepository.getAppTheme().toPresentationModel()
                )
            )
        }
    }

    private fun hideThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                themePicker = null,
            )
        }
    }

    private suspend fun setDynamicColor(enabled: Boolean) {
        settingsRepository.setDynamicColor(enabled)
    }

    private suspend fun setAppTheme(theme: ThemeWithText) {
        hideThemePicker()
        settingsRepository.setAppTheme(theme.toDomainModel())
    }
}

private fun initialState(): SettingsState = SettingsState(
    dynamicColor = dynamicColorState(),
    theme = themeState(systemTheme()),
    themePicker = null,
    topBar = TopBarState(title = R.string.title, icon = Icons.ArrowBack.Rounded),
)

private fun dynamicColorState() = DynamicColorState(
    checked = true,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.Outlined
)

private fun themeState(selectedTheme: ThemeWithText) = ThemeState(
    headline = R.string.theme_color_headline,
    supporting = R.string.theme_color_supporting,
    trailing = selectedTheme.text,
    icon = Icons.Colors.Rounded
)

private fun themePickerState(
    selectedTheme: ThemeWithText,
) = ThemePickerState(
    title = R.string.theme_color_picker_title,
    selectedTheme = selectedTheme,
    confirm = R.string.theme_color_picker_confirm,
    dismiss = R.string.theme_color_picker_dismiss,
    themes = themesList,
)

private val themesList = listOf(systemTheme(), lightTheme(), darkTheme())
private fun darkTheme() = ThemeWithText.DarkTheme(R.string.theme_dark)
private fun lightTheme() = ThemeWithText.LightTheme(R.string.theme_light)
private fun systemTheme() = ThemeWithText.SystemTheme(R.string.theme_system)