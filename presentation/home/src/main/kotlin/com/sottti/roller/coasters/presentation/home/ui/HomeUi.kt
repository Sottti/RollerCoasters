package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationDestination
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUi

@Composable
internal fun HomeUi(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNavigationDestination.NavigationBar.route,
    ) {
        composable(HomeNavigationDestination.NavigationBar.route) {
            NavigationBar(navHostController = navController, viewModel = viewModel)
        }

        composable(HomeNavigationDestination.Settings.route) {
            SettingsUi(navController = navController)
        }
    }
}