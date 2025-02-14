package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    rollerCoastersRepository: RollerCoastersRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreState(null))
    val state: StateFlow<ExploreState> = _state.asStateFlow()
}