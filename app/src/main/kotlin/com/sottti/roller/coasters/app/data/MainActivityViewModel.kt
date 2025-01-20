package com.sottti.roller.coasters.app.data

import androidx.lifecycle.ViewModel
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.app.model.NavigationBarActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        MainActivityState(
            navigationBarItems(),
            topBar = TopBarState(settingsIcon = Icons.Settings.Outlined),
        )
    )
    val state: StateFlow<MainActivityState> = _state.asStateFlow()

    val actions = NavigationBarActions(
        onDestinationSelected = { destination ->
            _state.update { it.copy(navigationBarItems = navigationBarItems(destination)) }
        }
    )
}