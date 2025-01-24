package com.sottti.roller.coasters.presentation.home.navigation

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class HomeNavigationBarDestination(
    val route: String,
) {
    data object AboutMe : HomeNavigationBarDestination(route = "aboutMe")
    data object Explore : HomeNavigationBarDestination(route = "explore")
    data object Favourites : HomeNavigationBarDestination(route = "favourites")
}