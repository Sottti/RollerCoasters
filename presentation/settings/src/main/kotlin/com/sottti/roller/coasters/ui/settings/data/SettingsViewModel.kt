package com.sottti.roller.coasters.ui.settings.data

import androidx.lifecycle.ViewModel
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.settings.R
import com.sottti.roller.coasters.ui.settings.model.DynamicColorState
import com.sottti.roller.coasters.ui.settings.model.SettingsActions
import com.sottti.roller.coasters.ui.settings.model.SettingsState
import com.sottti.roller.coasters.ui.settings.model.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(settingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    val actions = SettingsActions({})
}

private fun settingsState(): SettingsState {
    return SettingsState(
        topBar = TopBarState(title = R.string.title, icon = Icons.ArrowBack.Rounded),
        dynamicColor = DynamicColorState(
            checked = true,
            headline = R.string.dynamic_color_headline,
            supporting = R.string.dynamic_color_supporting,
            icon = Icons.Palette.Outlined
        ),
    )
}