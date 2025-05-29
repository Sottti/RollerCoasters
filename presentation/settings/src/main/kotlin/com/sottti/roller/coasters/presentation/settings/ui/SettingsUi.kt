package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview
import com.sottti.roller.coasters.presentation.settings.data.SettingsViewModel
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.ui.dialogs.Dialogs

@Composable
public fun SettingsUi(
    onBackNavigation: () -> Unit,
) {
    SettingsUi(
        onBackNavigation = onBackNavigation,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SettingsUi(
    onBackNavigation: () -> Unit,
    viewModel: SettingsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsUi(
        onAction = viewModel.onAction,
        onBackNavigation = onBackNavigation,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SettingsUi(
    onAction: (SettingsAction) -> Unit,
    onBackNavigation: () -> Unit,
    state: SettingsState,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(onBackNavigation, scrollBehavior, state.topBar) },
    ) { paddingValues ->
        SettingsList(
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            paddingValues = paddingValues,
            state = state,
        )
        Dialogs(
            state = state,
            onAction = onAction,
        )
    }
}

@Composable
@LightDarkThemePreview
internal fun SettingsUiPreview(
    @PreviewParameter(SettingsUiStateProvider::class)
    state: SettingsPreviewState,
) {
    RollerCoastersPreviewTheme {
        SettingsUi(
            onAction = state.onAction,
            onBackNavigation = state.onBackNavigation,
            state = state.state,
        )
    }
}

@Composable
@LightDarkThemePreview
internal fun SettingsUiPickersPreview(
    @PreviewParameter(SettingsUiPickersStateProvider::class)
    state: SettingsPreviewState,
) {
    RollerCoastersPreviewTheme {
        SettingsUi(
            onAction = state.onAction,
            onBackNavigation = state.onBackNavigation,
            state = state.state,
        )
    }
}