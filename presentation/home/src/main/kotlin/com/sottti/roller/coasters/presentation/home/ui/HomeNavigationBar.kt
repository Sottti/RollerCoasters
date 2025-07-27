package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Search
import com.sottti.roller.coasters.presentation.navigation.toNavigationDestination
import com.sottti.roller.coasters.presentation.search.ui.SearchUi

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun NavigationBar(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var bottomSheetContent by remember {
        mutableStateOf<(@Composable ColumnScope.() -> Unit)?>(null)
    }
    val showSheet: (@Composable ColumnScope.() -> Unit) -> Unit = { content ->
        bottomSheetContent = content
    }

    val navController = rememberNavController()
    val startDestination = Explore
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTab by rememberSaveable(stateSaver = NavigationDestination.saver) {
        mutableStateOf(startDestination)
    }

    val scrollToTopCallbacks = remember { mutableMapOf<NavigationDestination, () -> Unit>() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(navBackStackEntry?.destination?.route) {
        val currentDestination = navBackStackEntry?.destination?.route.toNavigationDestination()
        if (currentDestination != selectedTab) {
            selectedTab = currentDestination
            viewModel.actions.onDestinationSelected(currentDestination)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                navigationBarItems = state.items,
                onNavigationBarItemClick = { homeNavigationBarItem ->
                    val destination = homeNavigationBarItem.destination
                    when (selectedTab) {
                        destination -> scrollToTopCallbacks[destination]?.invoke()
                        else -> {
                            selectedTab = destination
                            viewModel.actions.onDestinationSelected(destination)
                            navController.navigateTo(homeNavigationBarItem)
                        }
                    }
                },
            )
        },
    ) { padding ->
        NavHost(
            navController = navController,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            onNavigateToSettings = onNavigateToSettings,
            padding = padding,
            scrollToTopCallbacks = scrollToTopCallbacks,
            showSheet = showSheet,
            startDestination = startDestination,
        )

        if (bottomSheetContent != null) {
            ModalBottomSheet(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                onDismissRequest = { bottomSheetContent = null },
                sheetState = sheetState,
            ) { bottomSheetContent?.invoke(this) }
        }
    }
}

@Composable
private fun NavHost(
    navController: NavHostController,
    startDestination: Explore,
    padding: PaddingValues,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    scrollToTopCallbacks: MutableMap<NavigationDestination, () -> Unit>,
    showSheet: (@Composable (ColumnScope.() -> Unit)) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Explore> {
            ExploreUi(
                onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                onNavigateToSettings = onNavigateToSettings,
                onScrollToTop = { callback -> scrollToTopCallbacks[Explore] = callback },
                padding = padding,
            )
        }
        composable<Favourites> {
            FavouritesUi(
                onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                onNavigateToSettings = onNavigateToSettings,
                onScrollToTop = { callback -> scrollToTopCallbacks[Favourites] = callback },
                padding = padding,
            )
        }
        composable<Search> {
            SearchUi(
                onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                onNavigateToSettings = onNavigateToSettings,
                onScrollToTop = { callback -> scrollToTopCallbacks[Search] = callback },
                padding = padding,
            )
        }

        composable<AboutMe> {
            AboutMeUi(
                onNavigateToSettings = onNavigateToSettings,
                onScrollToTop = { callback -> scrollToTopCallbacks[AboutMe] = callback },
                onShowBottomSheet = showSheet,
                padding = padding,
            )
        }
    }
}

private fun NavHostController.navigateTo(
    homeNavigationBarItem: HomeNavigationBarItem,
) {
    navigate(homeNavigationBarItem.destination) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}