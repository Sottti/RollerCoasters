package com.sottti.roller.coasters.presentation.settings.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.*
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.data.settings.model.Theme.DarkTheme
import com.sottti.roller.coasters.data.settings.model.Theme.LightTheme
import com.sottti.roller.coasters.data.settings.model.Theme.SystemTheme
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.*
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.ThemeState
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi
import com.sottti.roller.coasters.presentation.settings.model.TopBarState
import com.sottti.roller.coasters.utils.device.sdk.isDynamicColorEnabled
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
        observeTheme()
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

    private fun observeTheme() {
        viewModelScope.launch {
            settingsRepository
                .observeTheme()
                .collect { theme -> updateTheme(theme) }
        }
    }

    private fun updateTheme(theme: Theme) {
        _state.update { currentTheme ->
            currentTheme.copy(
                theme = currentTheme.theme.copy(
                    selectedTheme = SelectedThemeState.Loaded(
                        theme.toPresentationModel(currentTheme.theme.selectedTheme == theme),
                    )
                ),
            )
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> setDynamicColor(action.checked)

                LaunchThemePicker -> showThemePicker()
                is ThemePickerSelectionChange -> updateThemePicker(action.theme)
                is ConfirmThemePickerSelection -> setTheme(action.theme)
                DismissThemePicker -> hideThemePicker()

                LaunchColorContrastPicker -> showColorContrastPicker()
                is ColorContrastPickerSelectionChange -> TODO()
                DismissColorContrastPicker -> TODO()
            }
        }
    }

    private suspend fun showThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                themePicker = themePickerState(
                    selectedTheme = settingsRepository
                        .getTheme()
                        .toPresentationModel(isSelected = true)
                )
            )
        }
    }

    private suspend fun showColorContrastPicker() {
        _state.update { currentState ->
            currentState.copy(
                colorContrastPicker = colorContrastPickerState(
                    selectedColorContrast = settingsRepository.getColorContrast()
                )
            )
        }
    }

    private fun updateThemePicker(
        selectedTheme: ThemeUi,
    ) {
        _state.update { currentState ->
            currentState.copy(
                themePicker = themePickerState(selectedTheme = selectedTheme)
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

    private suspend fun setTheme(theme: ThemeUi) {
        hideThemePicker()
        settingsRepository.setTheme(theme.toDomainModel())
    }
}

private fun initialState(): SettingsState =
    SettingsState(
        colorContrast = colorContrastInitialState(),
        colorContrastPicker = null,
        dynamicColor = dynamicColorInitialState().takeIf { isDynamicColorEnabled() },
        theme = themeInitialState(),
        themePicker = null,
        topBar = topBarState(),
    )

private fun dynamicColorInitialState() = DynamicColorState(
    checkedState = DynamicColorCheckedState.Loading,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.Outlined
)

private fun themeInitialState() = ThemeState(
    headline = R.string.theme_headline,
    supporting = R.string.theme_supporting,
    selectedTheme = SelectedThemeState.Loading,
    icon = Icons.Colors.Rounded
)

private fun colorContrastInitialState() =
    ColorContrastState(
        headline = R.string.color_contrast_color_headline,
        icon = Icons.Contrast.Rounded,
        selectedColorContrast = SelectedColorContrastState.Loading,
        supporting = R.string.color_contrast_color_supporting,
    )

private fun topBarState(): TopBarState =
    TopBarState(title = R.string.title, icon = Icons.ArrowBack.Rounded)

private fun themePickerState(
    selectedTheme: ThemeUi,
) = ThemePickerState(
    title = R.string.theme_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    themes = themesList(selectedTheme),
)

private fun themesList(
    selectedTheme: ThemeUi,
) = listOf(
    SystemTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.SystemTheme),
    LightTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.LightTheme),
    DarkTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.DarkTheme),
)

private fun colorContrastPickerState(
    selectedColorContrast: ColorContrast,
) = ColorContrastPickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    contrasts = colorContrastsList(selectedColorContrast),
)

private fun colorContrastsList(
    selectedColorContrast: ColorContrast,
) = listOf(
    SystemContrast.toPresentationModel(isSelected = selectedColorContrast is SystemContrast),
    StandardContrast.toPresentationModel(isSelected = selectedColorContrast is StandardContrast),
    MediumContrast.toPresentationModel(isSelected = selectedColorContrast is MediumContrast),
    HighContrast.toPresentationModel(isSelected = selectedColorContrast is HighContrast),
)
