package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUi
import com.sottti.roller.coasters.presentation.explore.ui.ExploreUi
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesUi
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination

@Composable
internal fun HomeNavHost(
    navController: NavHostController,
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavigationBarDestination.Explore.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(HomeNavigationBarDestination.Explore.route) {
            ExploreUi(nestedScrollConnection = nestedScrollConnection)
        }
        composable(HomeNavigationBarDestination.Favourites.route) {
            FavouritesUi(nestedScrollConnection = nestedScrollConnection)
        }
        composable(HomeNavigationBarDestination.AboutMe.route) {
            AboutMeUi(nestedScrollConnection = nestedScrollConnection)
        }
    }
}