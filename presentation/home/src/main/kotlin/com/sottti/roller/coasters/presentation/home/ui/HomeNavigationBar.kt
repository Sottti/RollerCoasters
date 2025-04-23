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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUi
import com.sottti.roller.coasters.presentation.explore.ui.ExploreUi
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesUi
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.AboutMe
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Explore
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Favourites
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Settings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun NavigationBar(
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel,
) {
    val navController = rememberNavController()
    val startDestination = Explore
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedTab by remember { mutableStateOf(startDestination) }
    val scrollToTopCallbacks = remember { mutableMapOf<NavigationDestination, () -> Unit>() }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                navController = navController,
                navigationBarItems = state.items,
                actions = viewModel.actions,
                onNavigationBarItemClick = { homeNavigationBarItem ->
                    if (selectedTab == homeNavigationBarItem.destination) {
                        scrollToTopCallbacks[homeNavigationBarItem.destination]?.invoke()
                    }
                },
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            composable<Explore> {
                ExploreUi(
                    onScrollToTop = { callback -> scrollToTopCallbacks[Explore] = callback },
                    onNavigateToSettings = onNavigateToSettings,
                )
            }
            composable<Favourites> { FavouritesUi() }
            composable<AboutMe> { AboutMeUi() }
        }
    }
}