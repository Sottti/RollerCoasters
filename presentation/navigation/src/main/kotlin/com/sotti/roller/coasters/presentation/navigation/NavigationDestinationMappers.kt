package com.sotti.roller.coasters.presentation.navigation

import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.AboutMe
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Explore
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Favourites
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Home
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Search
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.Settings

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
