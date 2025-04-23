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
    viewModel: SettingsViewModel,
    onBackNavigation: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val nestedScrollConnection = scrollBehavior.nestedScrollConnection

    Scaffold(
        topBar = {
            TopBar(
                onBackNavigation = onBackNavigation,
                scrollBehavior = scrollBehavior,
                state = state.topBar,
            )
        },
    ) { paddingValues ->
        SettingsList(
            nestedScrollConnection = nestedScrollConnection,
            paddingValues = paddingValues,
            state = state,
            onAction = viewModel.onAction
        )
        Dialogs(state = state, onAction = viewModel.onAction)
    }
}