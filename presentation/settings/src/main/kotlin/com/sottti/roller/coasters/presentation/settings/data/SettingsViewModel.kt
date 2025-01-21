package com.sottti.roller.coasters.presentation.settings.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SettingsActions
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.TopBarState
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

    val actions = SettingsActions(
        onDynamicColorCheckedChange = { setDynamicColor(enabled = it) }
    )

    init {
        viewModelScope.launch {
            settingsRepository
                .observeDynamicColor()
                .collect { dynamicColorChecked ->
                    _state.update { currentState ->
                        currentState.copy(
                            dynamicColor = currentState
                                .dynamicColor
                                .copy(checked = dynamicColorChecked)
                        )
                    }
                }
        }
    }

    private fun setDynamicColor(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setDynamicColor(enabled)
        }
    }
}

private fun initialState(): SettingsState =
    SettingsState(
        topBar = TopBarState(title = R.string.title, icon = Icons.ArrowBack.Rounded),
        dynamicColor = DynamicColorState(
            checked = true,
            headline = R.string.dynamic_color_headline,
            supporting = R.string.dynamic_color_supporting,
            icon = Icons.Palette.Outlined
        ),
    )