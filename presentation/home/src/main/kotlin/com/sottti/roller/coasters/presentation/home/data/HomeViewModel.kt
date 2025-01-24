package com.sottti.roller.coasters.presentation.home.data

import androidx.lifecycle.ViewModel
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.home.R
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItems
import com.sottti.roller.coasters.presentation.home.model.NavigationBarState
import com.sottti.roller.coasters.presentation.home.model.TopBarState
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        NavigationBarState(
            navigationBarItems(),
            topBar = TopBarState(settingsIcon = Icons.Settings.Outlined),
        )
    )
    val state: StateFlow<NavigationBarState> = _state.asStateFlow()

    val actions = HomeActions(
        onDestinationSelected = { destination ->
            _state.update { it.copy(items = navigationBarItems(destination)) }
        }
    )
}

internal fun navigationBarItems(
    selectedItem: HomeNavigationBarDestination = HomeNavigationBarDestination.Explore,
): HomeNavigationBarItems =
    HomeNavigationBarItems(
        items = listOf(explore(selectedItem), favourites(selectedItem), aboutMe(selectedItem)),
        selectedItem = selectedItem,
    )


private fun explore(
    selectedItem: HomeNavigationBarDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_explore,
    icon = when (selectedItem) {
        HomeNavigationBarDestination.Explore -> Icons.Explore.Filled
        else -> Icons.Explore.Outlined
    },
    destination = HomeNavigationBarDestination.Explore,
)

private fun favourites(
    selectedItem: HomeNavigationBarDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    icon = when (selectedItem) {
        HomeNavigationBarDestination.Favourites -> Icons.Star.Filled
        else -> Icons.Star.Outlined
    },
    destination = HomeNavigationBarDestination.Favourites,
)

private fun aboutMe(
    selectedItem: HomeNavigationBarDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    icon = when (selectedItem) {
        HomeNavigationBarDestination.AboutMe -> Icons.AccountCircle.Filled
        else -> Icons.AccountCircle.Outlined
    },
    destination = HomeNavigationBarDestination.AboutMe,
)