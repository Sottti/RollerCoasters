package com.sottti.roller.coasters.presentation.settings.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.data.settings.model.Language.EnglishGbLanguage
import com.sottti.roller.coasters.data.settings.model.Language.GalicianLanguage
import com.sottti.roller.coasters.data.settings.model.Language.SpanishSpainLanguage
import com.sottti.roller.coasters.data.settings.model.Language.SystemLanguage
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.data.settings.model.Theme.DarkTheme
import com.sottti.roller.coasters.data.settings.model.Theme.LightTheme
import com.sottti.roller.coasters.data.settings.model.Theme.SystemTheme
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastListItemState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastNotAvailableMessageState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.LanguageListItemState
import com.sottti.roller.coasters.presentation.settings.model.LanguagePickerState
import com.sottti.roller.coasters.presentation.settings.model.LanguageState
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemeListItemState
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.ThemeState
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi
import com.sottti.roller.coasters.presentation.settings.model.TopBarState
import com.sottti.roller.coasters.utils.device.sdk.isColorContrastAvailable
import com.sottti.roller.coasters.utils.device.sdk.isDynamicColorAvailable
import com.sottti.roller.coasters.utils.device.sdk.isLightDarkThemeSystemAvailable
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
        if (isDynamicColorAvailable()) observeDynamicColor()
        observeTheme()
        observeColorContrast()
        updateLanguage()
    }

    private fun observeDynamicColor() {
        viewModelScope.launch {
            settingsRepository.observeDynamicColor()
                .collect { dynamicColorChecked -> updateDynamicColor(dynamicColorChecked) }
        }
    }

    private fun updateDynamicColor(dynamicColorChecked: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                dynamicColor = currentState.dynamicColor?.copy(
                    checkedState = DynamicColorCheckedState.Loaded(dynamicColorChecked)
                )
            )
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            settingsRepository.observeTheme().collect { theme -> updateTheme(theme) }
        }
    }

    private fun updateTheme(theme: Theme) {
        _state.update { currentState ->
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

    private fun observeColorContrast() {
        viewModelScope.launch {
            settingsRepository.observeColorContrast()
                .collect { colorContrast -> updateColorContrast(colorContrast) }
        }
    }

    private fun updateColorContrast(colorContrast: ColorContrast) {
        _state.update { currentState ->
            currentState.copy(
                colorContrast = currentState.colorContrast.copy(
                    listItem = currentState.colorContrast.listItem.copy(
                        selectedColorContrast = SelectedColorContrastState.Loaded(
                            colorContrast.toPresentationModel(isSelected = true),
                        )
                    ),
                )
            )
        }
    }

    private fun updateLanguage() {
        viewModelScope.launch {
            val selectedLanguage = settingsRepository.getLanguage()
            _state.update { currentState ->
                currentState.copy(
                    language = currentState.language.copy(
                        listItem = currentState.language.listItem.copy(
                            selectedLanguage = SelectedLanguageState.Loaded(
                                selectedLanguage.toPresentationModel(isSelected = true),
                            )
                        ),
                    )
                )
            }
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> {
                    settingsRepository.setDynamicColor(action.checked)
                    if (action.checked == true) {
                        settingsRepository.setColorContrast(SystemContrast)
                    }
                }

                LaunchThemePicker -> showThemePicker()
                is ThemePickerSelectionChange -> updateThemePicker(action.theme)
                is ConfirmThemePickerSelection -> {
                    hideThemePicker()
                    settingsRepository.setTheme(action.theme.toDomainModel())
                }

                DismissThemePicker -> hideThemePicker()

                LaunchColorContrastPicker -> showColorContrastPicker()
                is ColorContrastPickerSelectionChange -> updateColorContrastPicker(action.contrast)
                is ConfirmColorContrastPickerSelection -> {
                    hideColorContrastPicker()
                    settingsRepository.setColorContrast(action.contrast.toDomainModel())
                }

                DismissColorContrastPicker -> hideColorContrastPicker()
                DismissColorContrastNotAvailableMessage -> hideColorContrastNotAvailableMessage()

                LaunchLanguagePicker -> showLanguagePicker()
                is LanguagePickerSelectionChange -> updateLanguagePicker(action.language)
                is ConfirmLanguagePickerSelection -> {
                    hideLanguagePicker()
                    settingsRepository.setLanguage(action.language.toDomainModel())
                    updateLanguage()
                }

                DismissLanguagePicker -> hideLanguagePicker()
            }
        }
    }

    private suspend fun showThemePicker() {
        _state.update { currentState ->
            currentState.copy(
                theme = currentState.theme.copy(
                    picker = themePickerState(
                        selectedTheme = settingsRepository.getTheme()
                            .toPresentationModel(isSelected = true)
                    )
                )
            )
        }
    }

    private suspend fun showColorContrastPicker() {
        _state.update { currentState ->
            when {
                currentState.isDynamicColorChecked() -> currentState.copy(
                    currentState.colorContrast.copy(
                        picker = null,
                        notAvailableMessage = contrastNotAvailableMessageState(),
                    )
                )

                else -> currentState.copy(
                    colorContrast = currentState.colorContrast.copy(
                        notAvailableMessage = null, picker = colorContrastPickerState(
                            selectedColorContrast = settingsRepository.getColorContrast()
                                .toPresentationModel(isSelected = true)
                        )
                    )
                )
            }
        }
    }

    private fun updateThemePicker(
        selectedTheme: ThemeUi,
    ) {
        _state.update { currentState ->
            currentState.copy(
                theme = currentState.theme.copy(picker = themePickerState(selectedTheme)),
            )
        }
    }

    private fun hideThemePicker() {
        _state.update { currentState ->
            currentState.copy(theme = currentState.theme.copy(picker = null))
        }
    }

    private fun updateColorContrastPicker(
        selectedContrast: ColorContrastUi,
    ) {
        _state.update { currentState ->
            currentState.copy(
                colorContrast = currentState.colorContrast.copy(
                    picker = colorContrastPickerState(selectedColorContrast = selectedContrast)
                )
            )
        }
    }

    private fun hideColorContrastPicker() {
        _state.update { currentState ->
            currentState.copy(colorContrast = currentState.colorContrast.copy(picker = null))
        }
    }

    private fun hideColorContrastNotAvailableMessage() {
        _state.update { currentState ->
            currentState.copy(
                colorContrast = currentState.colorContrast.copy(notAvailableMessage = null)
            )
        }
    }

    private fun showLanguagePicker() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    language = currentState.language.copy(
                        picker = languagePickerState(
                            settingsRepository.getLanguage().toPresentationModel(isSelected = true)
                        ),
                    )
                )
            }
        }
    }

    private fun updateLanguagePicker(
        selectedLanguage: LanguageUi,
    ) {
        _state.update { currentState ->
            currentState.copy(
                language = currentState
                    .language
                    .copy(picker = languagePickerState(selectedLanguage)),
            )
        }
    }

    private fun hideLanguagePicker() {
        _state.update { currentState ->
            currentState.copy(
                language = currentState.language.copy(picker = null)
            )
        }
    }
}

private fun initialState(): SettingsState = SettingsState(
    colorContrast = colorContrastInitialState(),
    dynamicColor = dynamicColorInitialState().takeIf { isDynamicColorAvailable() },
    language = languageInitialState(),
    theme = themeInitialState(),
    topBar = topBarState(),
)

private fun colorContrastInitialState() = ColorContrastState(
    listItem = ColorContrastListItemState(
        headline = R.string.color_contrast_color_headline,
        icon = Icons.Visibility.Outlined,
        selectedColorContrast = SelectedColorContrastState.Loading,
        supporting = R.string.color_contrast_color_supporting,
    ),
    notAvailableMessage = null,
    picker = null,
)

private fun dynamicColorInitialState() = DynamicColorState(
    checkedState = DynamicColorCheckedState.Loading,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.Outlined,
)

private fun languageInitialState() = LanguageState(
    listItem = LanguageListItemState(
        headline = R.string.language_headline,
        icon = Icons.Language.Rounded,
        supporting = R.string.language_supporting,
        selectedLanguage = SelectedLanguageState.Loading,
    ),
    picker = null,
)

private fun SettingsState.isDynamicColorChecked() =
    dynamicColor?.checkedState is DynamicColorCheckedState.Loaded && dynamicColor.checkedState.checked

private fun themeInitialState() = ThemeState(
    listItem = ThemeListItemState(
        headline = R.string.theme_headline,
        supporting = R.string.theme_supporting,
        selectedTheme = SelectedThemeState.Loading,
        icon = Icons.BrightnessMedium.Outlined
    ),
    picker = null,
)

private fun contrastNotAvailableMessageState(): ColorContrastNotAvailableMessageState =
    ColorContrastNotAvailableMessageState(
        dismiss = R.string.picker_dismiss,
        text = R.string.color_contrast_not_available_message_text,
        title = R.string.color_contrast_not_available_message_title,
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
) = listOfNotNull(
    SystemTheme.toPresentationModel(isSelected = selectedTheme is ThemeUi.SystemTheme)
        .takeIf { isLightDarkThemeSystemAvailable() },
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
) = listOfNotNull(
    SystemContrast.toPresentationModel(
        isSelected = selectedColorContrast is ColorContrastUi.SystemContrast,
    ).takeIf { isColorContrastAvailable() },
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

private fun languagePickerState(
    selectedLanguage: LanguageUi,
) = LanguagePickerState(
    title = R.string.color_contrast_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    languages = languagesList(selectedLanguage),
)

private fun languagesList(
    selectedLanguage: LanguageUi,
) = listOfNotNull(
    SystemLanguage.toPresentationModel(isSelected = selectedLanguage is LanguageUi.SystemLanguage),
    EnglishGbLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.EnglishGbLanguage,
    ),
    SpanishSpainLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.SpanishSpainLanguage,
    ),
    GalicianLanguage.toPresentationModel(
        isSelected = selectedLanguage is LanguageUi.GalicianLanguage,
    ),


    )