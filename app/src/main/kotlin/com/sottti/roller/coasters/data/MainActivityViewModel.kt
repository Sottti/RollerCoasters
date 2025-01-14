package com.sottti.roller.coasters.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.model.NavigationBarActions
import com.sottti.roller.coasters.model.NavigationBarItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainActivityViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(navigationBarItems())
    val state: StateFlow<NavigationBarItems> = _viewState.asStateFlow()

    val actions = NavigationBarActions(
        onDestinationSelected = { destination ->
            _viewState.value = navigationBarItems(destination)
        }
    )
}