package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.settings.data.SettingsViewModel

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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(onBackNavigation, scrollBehavior, state.topBar) }
    ) { paddingValues ->
        SettingsList(
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = viewModel.onAction,
            paddingValues = paddingValues,
            state = state,
        )
        Dialogs(state = state, onAction = viewModel.onAction)
    }
}