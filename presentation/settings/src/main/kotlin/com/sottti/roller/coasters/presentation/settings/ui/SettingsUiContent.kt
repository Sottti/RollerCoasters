package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.ui.dialogs.SettingsDialogs

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SettingsUiContent(
    onBackNavigation: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SettingsState,
    onAction: (SettingsAction) -> Unit,
) {
    Scaffold(
        topBar = { TopBar(onBackNavigation, scrollBehavior, state.topBar) },
    ) { paddingValues ->
        SettingsList(
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            paddingValues = paddingValues,
            state = state,
        )
        SettingsDialogs(
            state = state,
            onAction = onAction,
        )
    }
}