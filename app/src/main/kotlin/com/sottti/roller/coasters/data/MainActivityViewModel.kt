package com.sottti.roller.coasters.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.model.NavigationBarActions
import com.sottti.roller.coasters.model.NavigationBarItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainActivityViewModel : ViewModel() {
    private val _state = MutableStateFlow(navigationBarItems())
    val state: StateFlow<NavigationBarItems> = _state.asStateFlow()

    val actions = NavigationBarActions(
        onDestinationSelected = { destination ->
            _state.value = navigationBarItems(destination)
        }
    )
}