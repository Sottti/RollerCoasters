package com.sottti.roller.coasters.app.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.app.data.MainActivityViewModel
import com.sottti.roller.coasters.app.navigation.NavigationDestination
import com.sottti.roller.coasters.ui.settings.ui.Settings

@Composable
internal fun MainActivityContent(
    viewModel: MainActivityViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = NavigationDestination.NavigationBar.route,
    ) {
        composable(NavigationDestination.NavigationBar.route) {
            NavigationBar(mainNavHostController = navController, viewModel = viewModel)
        }

        composable(NavigationDestination.Settings.route) {
            Settings(navController = navController)
        }
    }
}