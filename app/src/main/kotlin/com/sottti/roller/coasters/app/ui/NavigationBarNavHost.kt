package com.sottti.roller.coasters.app.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sottti.roller.coasters.app.navigation.NavigationBarDestination
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMe
import com.sottti.roller.coasters.presentation.favourites.ui.Favourites
import com.sottti.roller.coasters.presentation.home.ui.Home

@Composable
internal fun NavigationBarNavHost(
    navController: NavHostController,
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationBarDestination.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(NavigationBarDestination.Home.route) {
            Home(nestedScrollConnection = nestedScrollConnection)
        }
        composable(NavigationBarDestination.Favourites.route) {
            Favourites(nestedScrollConnection = nestedScrollConnection)
        }
        composable(NavigationBarDestination.AboutMe.route) {
            AboutMe(nestedScrollConnection = nestedScrollConnection)
        }
    }
}