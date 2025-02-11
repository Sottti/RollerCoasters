package com.sottti.roller.coasters.presentation.home.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.NavigationBarState
import com.sottti.roller.coasters.presentation.home.model.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val rollerCoastersRepository: RollerCoastersRepository,
) : ViewModel() {
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