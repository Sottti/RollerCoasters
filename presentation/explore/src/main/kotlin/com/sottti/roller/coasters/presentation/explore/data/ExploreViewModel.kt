package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearSortBySecondaryFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearTypeSecondaryFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByDrop
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByGForce
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByInversions
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByLength
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByMaxVertical
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortBySpeed
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
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

    private val _rollerCoastersFlow: Flow<PagingData<ExploreRollerCoaster>> =
        rollerCoastersRepository
            .getRollerCoastersSortedByHeight()
            .toUiModel()
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(ExploreInitialStates.state(_rollerCoastersFlow))

    val state: StateFlow<ExploreState> = _state.asStateFlow()

    internal val onAction: (ExploreAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: ExploreAction) {
        when (action) {
            is PrimaryFilterAction -> processPrimaryFilterAction(action)
            is SecondaryFilterAction -> processSecondaryFilterAction(action)
        }
    }

    private fun processPrimaryFilterAction(
        action: PrimaryFilterAction,
    ) {
        when (action) {
            ShowSortFilters -> _state.expandSortByPrimaryFilter()
            HideSortFilters -> _state.collapseSortByPrimaryFilter()

            ShowTypeFilters -> _state.expandTypePrimaryFilter()
            HideTypeFilters -> _state.collapseTypePrimaryFilter()
        }

    }

    private fun processSecondaryFilterAction(
        action: SecondaryFilterAction,
    ) {
        when (action) {
            ClearSortBySecondaryFilters -> _state.clearSortByFilters()
            ClearTypeSecondaryFilters -> _state.clearTypeFilters()
            SelectSortByDrop -> _state.selectSortByDrop()
            SelectSortByGForce -> _state.selectSortByGForce()
            SelectSortByHeight -> _state.selectSortByHeight()
            SelectSortByInversions -> _state.selectSortByInversions()
            SelectSortByLength -> _state.selectSortByLength()
            SelectSortByMaxVertical -> _state.selectSortByMaxVertical()
            SelectSortBySpeed -> _state.selectSortBySpeed()
            SelectTypeSteel -> _state.selectTypeSteel()
            SelectTypeWood -> _state.selectTypeWood()
        }

    }
}