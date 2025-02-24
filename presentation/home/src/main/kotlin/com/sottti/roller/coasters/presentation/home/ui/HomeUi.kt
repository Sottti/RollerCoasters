package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Composable
internal fun HomeUi(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Home.route,
    ) {
        composable(NavigationDestination.Home.route) {
            NavigationBar(viewModel = viewModel)
        }
    }
}