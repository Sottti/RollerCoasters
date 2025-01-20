package com.sottti.roller.coasters.ui.favourites.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sottti.roller.coasters.ui.favourites.data.SettingsViewModel

@Composable
fun Settings(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    Scaffold(
        topBar = { TopBar(navController) }
    ) { paddingValues ->
        SettingsContent(paddingValues)
    }
}