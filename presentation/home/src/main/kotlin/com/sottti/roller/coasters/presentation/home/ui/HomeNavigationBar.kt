package com.sottti.roller.coasters.presentation.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUi
import com.sottti.roller.coasters.presentation.explore.ui.ExploreUi
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesUi
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.AboutMe
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Explore
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Favourites

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun NavigationBar(
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val nestedNavController = rememberNavController()
    val startDestination = Explore
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedTab by remember { mutableStateOf(startDestination) }
    val scrollToTopCallbacks = remember { mutableMapOf<NavigationDestination, () -> Unit>() }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                navigationBarItems = state.items,
                onNavigationBarItemClick = { homeNavigationBarItem ->
                    val destination = homeNavigationBarItem.destination
                    if (selectedTab == destination) scrollToTopCallbacks[destination]?.invoke()
                    viewModel.actions.onDestinationSelected(homeNavigationBarItem.destination)
                    nestedNavController.navigateTo(homeNavigationBarItem)
                },
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = nestedNavController,
            startDestination = startDestination,
        ) {
            composable<Explore> {
                ExploreUi(
                    onNavigateToSettings = onNavigateToSettings,
                    onScrollToTop = { callback -> scrollToTopCallbacks[Explore] = callback },
                )
            }
            composable<Favourites> { FavouritesUi() }
            composable<AboutMe> { AboutMeUi() }
        }
    }
}

private fun NavHostController.navigateTo(
    homeNavigationBarItem: HomeNavigationBarItem,
) {
    navigate(homeNavigationBarItem.destination) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}