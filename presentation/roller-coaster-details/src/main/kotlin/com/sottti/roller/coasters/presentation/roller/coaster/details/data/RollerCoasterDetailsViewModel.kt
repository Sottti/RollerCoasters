package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails.Companion.KEY_ROLLER_COASTER_ID
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val observeRollerCoaster: ObserveRollerCoaster,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    internal val state: StateFlow<RollerCoasterDetailsState> = _state.asStateFlow()

    val rollerCoasterId: Int = savedStateHandle[KEY_ROLLER_COASTER_ID]
        ?: throw IllegalArgumentException("rollerCoasterId is required")

    init {
        viewModelScope.launch {
            observeRollerCoaster(RollerCoasterId(rollerCoasterId))
                .collect { _state.updateRollerCoaster(it) }
        }
    }
}