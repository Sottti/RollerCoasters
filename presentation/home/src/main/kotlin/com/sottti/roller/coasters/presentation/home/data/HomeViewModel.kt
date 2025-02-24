package com.sottti.roller.coasters.presentation.home.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.NavigationBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        NavigationBarState(navigationBarItems())
    )
    val state: StateFlow<NavigationBarState> = _state.asStateFlow()

    val actions = HomeActions(
        onDestinationSelected = { destination ->
            _state.update { it.copy(items = navigationBarItems(destination)) }
        }
    )
}