package com.sottti.roller.coasters.app.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.app.model.NavigationBarActions
import com.sottti.roller.coasters.app.model.NavigationBarItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(navigationBarItems())
    val state: StateFlow<NavigationBarItems> = _state.asStateFlow()

    val actions = NavigationBarActions(
        onDestinationSelected = { destination ->
            _state.update { navigationBarItems(destination) }
        }
    )
}