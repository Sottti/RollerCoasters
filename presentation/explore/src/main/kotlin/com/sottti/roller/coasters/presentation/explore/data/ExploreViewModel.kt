package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    rollerCoastersRepository: RollerCoastersRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreState(null))
    val state: StateFlow<ExploreState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val url = rollerCoastersRepository
                .getRollerCoaster(RollerCoasterId(760))
                .pictures.main?.url
            _state.update { currentState -> currentState.copy(url = url) }
        }
    }
}