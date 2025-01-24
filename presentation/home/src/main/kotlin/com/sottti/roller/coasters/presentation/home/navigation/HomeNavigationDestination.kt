package com.sottti.roller.coasters.presentation.home.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class HomeNavigationDestination(
    val route: String,
) {
    data object NavigationBar : HomeNavigationDestination(route = "navigationBar")
    data object Settings : HomeNavigationDestination(route = "settings")
}