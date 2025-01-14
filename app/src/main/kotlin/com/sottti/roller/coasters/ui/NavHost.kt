package com.sottti.roller.coasters.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sottti.roller.coasters.model.NavigationBarDestination

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