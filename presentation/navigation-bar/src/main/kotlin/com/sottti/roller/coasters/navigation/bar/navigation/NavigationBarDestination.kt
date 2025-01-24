package com.sottti.roller.coasters.navigation.bar.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class NavigationBarDestination(
    val route: String,
) {
    data object AboutMe : NavigationBarDestination(route = "aboutMe")
    data object Favourites : NavigationBarDestination(route = "favourites")
    data object Explore : NavigationBarDestination(route = "explore")
}