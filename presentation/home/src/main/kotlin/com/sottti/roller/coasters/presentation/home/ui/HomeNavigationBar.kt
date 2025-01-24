package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUi
import com.sottti.roller.coasters.presentation.explore.ui.ExploreUi
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesUi
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination.AboutMe
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination.Explore
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination.Favourites

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun NavigationBar(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val nestedScrollConnection = scrollBehavior.nestedScrollConnection
    val navigationBarNavHost = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController = navHostController,
                scrollBehavior = scrollBehavior,
                state = state.topBar
            )
        },
        bottomBar = {
            BottomBar(
                navController = navigationBarNavHost,
                navigationBarItems = state.items,
                actions = viewModel.actions,
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navigationBarNavHost,
            startDestination = Explore.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Explore.route) {
                ExploreUi(nestedScrollConnection = nestedScrollConnection)
            }
            composable(Favourites.route) {
                FavouritesUi(nestedScrollConnection = nestedScrollConnection)
            }
            composable(AboutMe.route) {
                AboutMeUi(nestedScrollConnection = nestedScrollConnection)
            }
        }
    }
}