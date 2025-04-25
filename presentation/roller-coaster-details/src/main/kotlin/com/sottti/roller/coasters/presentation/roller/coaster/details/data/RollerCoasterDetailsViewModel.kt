package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    private val dateFormatter: DateFormatter,
    private val observeRollerCoaster: ObserveRollerCoaster,
    private val rollerCoasterId: RollerCoasterId,

    ) : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    internal val state: StateFlow<RollerCoasterDetailsViewState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            observeRollerCoaster(rollerCoasterId)
                .collect { rollerCoaster ->
                    _state.updateRollerCoaster(
                        dateFormatter = dateFormatter,
                        rollerCoaster = rollerCoaster,
                    )
                }
        }
    }
}