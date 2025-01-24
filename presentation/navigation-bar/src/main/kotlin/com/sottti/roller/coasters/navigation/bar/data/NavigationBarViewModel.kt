package com.sottti.roller.coasters.navigation.bar.data

import androidx.lifecycle.ViewModel
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.navigation.bar.model.NavigationBarActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class NavigationBarViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        NavigationBarState(
            navigationBarItems(),
            topBar = TopBarState(settingsIcon = Icons.Settings.Outlined),
        )
    )
    val state: StateFlow<NavigationBarState> = _state.asStateFlow()

    val actions = NavigationBarActions(
        onDestinationSelected = { destination ->
            _state.update { it.copy(items = navigationBarItems(destination)) }
        }
    )
}