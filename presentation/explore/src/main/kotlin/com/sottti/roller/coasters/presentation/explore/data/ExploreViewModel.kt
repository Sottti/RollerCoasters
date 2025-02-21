package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.RollerCoasterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    rollerCoastersRepository: RollerCoastersRepository,
) : ViewModel() {

    private val _rollerCoastersFlow: Flow<PagingData<RollerCoasterUiModel>> =
        rollerCoastersRepository
            .getRollerCoastersSortedByHeight()
            .toUiModel()
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(
        ExploreState(rollerCoastersFlow = _rollerCoastersFlow)
    )
    val state: StateFlow<ExploreState> = _state.asStateFlow()
}