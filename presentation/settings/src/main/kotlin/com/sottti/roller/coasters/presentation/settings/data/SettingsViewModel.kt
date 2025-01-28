package com.sottti.roller.coasters.presentation.settings.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.data.settings.model.Theme.DarkTheme
import com.sottti.roller.coasters.data.settings.model.Theme.LightTheme
import com.sottti.roller.coasters.data.settings.model.Theme.SystemTheme
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemePickerSelectionChange
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
        observeColorContrast()
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
        _state.update { currentState ->
            currentState.copy(
                theme = currentState.theme.copy(
                    selectedTheme = SelectedThemeState.Loaded(
                        theme.toPresentationModel(currentState.theme.selectedTheme == theme),
                    )
                ),
            )
        }
    }

    private fun observeColorContrast() {
        viewModelScope.launch {
            settingsRepository
                .observeColorContrast()
                .collect { colorContrast -> updateColorContrast(colorContrast) }
        }
    }

    private fun updateColorContrast(colorContrast: ColorContrast) {
        _state.update { currentState ->
            currentState.copy(
                colorContrast = currentState.colorContrast.copy(
                    selectedColorContrast = SelectedColorContrastState.Loaded(
                        colorContrast.toPresentationModel(
                            currentState.colorContrast.selectedColorContrast == colorContrast,
                        ),
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
                is ColorContrastPickerSelectionChange -> updateColorContrastPicker(action.contrast)
                is ConfirmColorContrastPickerSelection -> setColorContrast(action.contrast)
                DismissColorContrastPicker -> hideColorContrastPicker()
                DismissColorContrastNotAvailableMessage -> hideColorContrastNotAvailableMessage()
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

    private fun SettingsState.isDynamicColorChecked() =
        dynamicColor?.checkedState is DynamicColorCheckedState.Loaded && dynamicColor.checkedState.checked

    private suspend fun showColorContrastPicker() {
        _state.update { currentState ->
            when {
                currentState.isDynamicColorChecked() -> currentState.copy(
                    colorContrastPicker = null,
                    colorContrastNotAvailableMessage = contrastNotAvailableMessageState(),
                )

                else -> currentState.copy(
                    colorContrastNotAvailableMessage = null,
                    colorContrastPicker = colorContrastPickerState(
                        selectedColorContrast = settingsRepository
                            .getColorContrast()
                            .toPresentationModel(isSelected = true)
                    )
                )
            }
        }
    }

    private fun contrastNotAvailableMessageState(): ColorContrastNotAvailableMessageState =
        ColorContrastNotAvailableMessageState(
            dismiss = R.string.picker_dismiss,
            text = R.string.color_contrast_not_available_message_text,
            title = R.string.color_contrast_not_available_message_title,
        )

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

    private suspend fun setColorContrast(contrast: ColorContrastUi) {
        hideColorContrastPicker()
        settingsRepository.setColorContrast(contrast.toDomainModel())
    }

    private fun updateColorContrastPicker(
        selectedContrast: ColorContrastUi,
    ) {
        _state.update { currentState ->
            currentState.copy(
                colorContrastPicker = colorContrastPickerState(
                    selectedColorContrast = selectedContrast,
                )
            )
        }
    }

    private fun hideColorContrastPicker() {
        _state.update { currentState ->
            currentState.copy(
                colorContrastPicker = null,
            )
        }
    }

    private fun hideColorContrastNotAvailableMessage() {
        _state.update { currentState ->
            currentState.copy(
                colorContrastNotAvailableMessage = null,
            )
        }
    }
}

private fun initialState(): SettingsState =
    SettingsState(
        colorContrast = colorContrastInitialState(),
        colorContrastNotAvailableMessage = null,
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
    selectedColorContrast: ColorContrastUi,
) = ColorContrastPickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    contrasts = colorContrastsList(selectedColorContrast),
)

private fun colorContrastsList(
    selectedColorContrast: ColorContrastUi,
) = listOf(
    SystemContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.SystemContrast,
    ),
    StandardContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.StandardContrast,
    ),
    MediumContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.MediumContrast,
    ),
    HighContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.HighContrast,
    ),
)