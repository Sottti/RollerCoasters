package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sottti.roller.coasters.presentation.settings.data.SettingsViewModel

@Composable
public fun SettingsUi(
    navController: NavHostController,
) {
    SettingsUiInternal(navController = navController)
}

@Composable
internal fun SettingsUiInternal(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopBar(navController = navController, state = state.topBar) },
    ) { paddingValues ->
        SettingsContent(paddingValues = paddingValues, state = state, onAction = viewModel.onAction)
    }
}