package com.sottti.roller.coasters.presentation.home.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItems
import com.sottti.roller.coasters.presentation.home.model.NavigationBarState
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {
    private val items = navigationBarItems()

    private val _state = MutableStateFlow(
        NavigationBarState(
            HomeNavigationBarItems(
                items = items,
                selectedItem = NavigationDestination.Explore,
            ),
        ),
    )
    val state: StateFlow<NavigationBarState> = _state.asStateFlow()

    val actions = HomeActions(
        onDestinationSelected = { destination ->
            _state.update { current ->
                current.copy(
                    items = current.items.copy(selectedItem = destination),
                )
            }
        }
    )
}
