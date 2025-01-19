package com.sottti.roller.coasters.app.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class NavigationDestination(
    val route: String,
) {
    data object NavigationBar : NavigationDestination(route = "navigationBar")
    data object Settings : NavigationDestination(route = "settings")
}