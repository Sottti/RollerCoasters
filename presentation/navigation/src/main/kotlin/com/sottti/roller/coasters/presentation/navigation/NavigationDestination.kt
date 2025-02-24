package com.sottti.roller.coasters.presentation.navigation

import androidx.compose.runtime.Immutable

@Immutable
public sealed class NavigationDestination(
    public val route: String,
) {
    public data object AboutMe : NavigationDestination(route = "aboutMe")
    public data object Explore : NavigationDestination(route = "explore")
    public data object Favourites : NavigationDestination(route = "favourites")
    public data object Home : NavigationDestination(route = "home")
    public data object Settings : NavigationDestination(route = "settings")
}