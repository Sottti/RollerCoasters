package com.sottti.roller.coasters.model

internal sealed class NavigationBarDestination(
    val route: String,
) {
    data object Home : NavigationBarDestination("home")
    data object Favourites : NavigationBarDestination("favourites")
    data object AboutMe : NavigationBarDestination("aboutMe")
}