package com.sottti.roller.coasters.navigation.bar.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.navigation.bar.data.NavigationBarViewModel
import com.sottti.roller.coasters.navigation.bar.navigation.NavigationDestination
import com.sottti.roller.coasters.presentation.settings.ui.Settings

@Composable
internal fun NavigationBarContent(
    viewModel: NavigationBarViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    NavHost(
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