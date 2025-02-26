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
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearSortHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortHeight
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

    private val _state = MutableStateFlow(initialState(_rollerCoastersFlow))

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
            ShowSortFilters -> _state.showSortByFilters()
            HideSortFilters -> _state.hideSortFilters()

            ShowTypeFilters -> _state.showTypeFilters()
            HideTypeFilters -> _state.hideTypeFilters()
        }

    }

    private fun processSecondaryFilterAction(
        action: SecondaryFilterAction,
    ) {
        when (action) {
            SelectSortHeight -> _state.selectSortByHeight()
            ClearSortHeight -> _state.clearSortByHeight()

            SelectTypeSteel -> TODO()
            ClearTypeSteel -> TODO()

            SelectTypeWood -> TODO()
            ClearTypeWood -> TODO()
        }

    }
}