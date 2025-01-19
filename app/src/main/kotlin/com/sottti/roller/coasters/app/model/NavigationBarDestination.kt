package com.sottti.roller.coasters.app.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class NavigationBarDestination(
    val route: String,
) {
    data object AboutMe : NavigationBarDestination(route = "aboutMe")
    data object Favourites : NavigationBarDestination(route = "favourites")
    data object Home : NavigationBarDestination(route = "home")

    companion object {
        fun fromRoute(route: String?): NavigationBarDestination? =
            listOf(AboutMe, Favourites, Home).find { it.route == route }
    }
}