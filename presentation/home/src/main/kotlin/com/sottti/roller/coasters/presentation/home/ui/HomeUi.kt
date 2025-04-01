package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUi

@Composable
internal fun HomeUi(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = NavigationDestination.Home.route,
    ) {
        composable(NavigationDestination.Home.route) {
            NavigationBar(viewModel = viewModel, rootNavController = rootNavController)
        }
        composable(NavigationDestination.Settings.route) {
            SettingsUi(navController = rootNavController)
        }
    }
}