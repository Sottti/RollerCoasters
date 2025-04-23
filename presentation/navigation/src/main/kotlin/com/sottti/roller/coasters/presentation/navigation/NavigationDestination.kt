package com.sottti.roller.coasters.presentation.navigation

import kotlinx.serialization.Serializable


public sealed class NavigationDestination {
    @Serializable
    public data object AboutMe : NavigationDestination()
    @Serializable
    public data object Explore : NavigationDestination()
    @Serializable
    public data object Favourites : NavigationDestination()
    @Serializable
    public data object Home : NavigationDestination()
    @Serializable
    public data object Settings : NavigationDestination()
}