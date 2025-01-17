package com.sottti.roller.coasters.app.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sottti.roller.coasters.app.model.NavigationBarDestination
import com.sottti.roller.coasters.ui.favourites.ui.Favourites
import com.sottti.roller.coasters.ui.home.ui.Home
import com.sottti.roller.coasters.ui.about.me.ui.AboutMe

@Composable
internal fun NavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationBarDestination.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(NavigationBarDestination.Home.route) { Home(paddingValues) }
        composable(NavigationBarDestination.Favourites.route) { Favourites(paddingValues) }
        composable(NavigationBarDestination.AboutMe.route) { AboutMe(paddingValues) }
    }
}