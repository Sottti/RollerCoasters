package com.sottti.roller.coasters.presentation.navigation

import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.AboutMe
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Explore
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Favourites
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Home
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Search
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Settings

public fun String?.toNavigationDestination(): NavigationDestination =
    when (this) {
        AboutMe::class.qualifiedName -> AboutMe
        Explore::class.qualifiedName -> Explore
        Favourites::class.qualifiedName -> Favourites
        Home::class.qualifiedName -> Home
        Search::class.qualifiedName -> Search
        Settings::class.qualifiedName -> Settings
        else -> Explore
    }