package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoasters
import com.sottti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.GetDefaultLocale
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByAlphabetical
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByDrop
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByGForce
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByInversions
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByLength
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByMaxVertical
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortBySpeed
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeAll
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreEvent
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.WoodFilter
import com.sottti.roller.coasters.presentation.format.UnitDisplayFormatter
import com.sottti.roller.coasters.presentation.string.provider.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    getAppLanguage: GetAppLanguage,
    getDefaultLocale: GetDefaultLocale,
    observeRollerCoasters: ObserveRollerCoasters,
    rollerCoastersRepository: RollerCoastersRepository,
    stringProvider: StringProvider,
    unitDisplayFormatter: UnitDisplayFormatter,
) : ViewModel() {

    private val _sortByFilter = MutableStateFlow(SortByFilter.Alphabetical)
    private val _typeFilter = MutableStateFlow(TypeFilter.All)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _rollerCoastersFlow: Flow<PagingData<ExploreRollerCoaster>> =
        combine(_typeFilter, _sortByFilter) { typeFilter, sortByFilter ->
            observeRollerCoasters(
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            ).toUiModel(
                appLanguage = getAppLanguage(),
                defaultLocale = getDefaultLocale(),
                sortByFilter = sortByFilter,
                stringProvider = stringProvider,
                unitDisplayFormatter = unitDisplayFormatter,
            )
        }.flatMapLatest { it }
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(initialState(_rollerCoastersFlow))
    val state: StateFlow<ExploreState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<ExploreEvent>()
    val events = _events.asSharedFlow()

    internal val onAction: (ExploreAction) -> Unit = { action -> processAction(action) }

    init {
        viewModelScope.launch {
            combine(
                _sortByFilter.distinctUntilChanged { old, new -> old == new },
                _typeFilter.distinctUntilChanged { old, new -> old == new },
            ) { sortBy, type -> sortBy to type }
                .collect { _events.emit(ExploreEvent.ScrollToTop) }
        }
    }

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
        updateSecondaryFilterQuery(action)
        updateSecondaryFilterUi(action)
    }

    private fun updateSecondaryFilterUi(
        action: SecondaryFilterAction,
    ) {
        when (action) {
            SelectSortByAlphabetical -> _state.select<AlphabeticalFilter>()
            SelectSortByDrop -> _state.select<DropFilter>()
            SelectSortByGForce -> _state.select<GForceFilter>()
            SelectSortByHeight -> _state.select<HeightFilter>()
            SelectSortByInversions -> _state.select<InversionsFilter>()
            SelectSortByLength -> _state.select<LengthFilter>()
            SelectSortByMaxVertical -> _state.select<MaxVerticalFilter>()
            SelectSortBySpeed -> _state.select<SpeedFilter>()
            SelectTypeAll -> _state.select<AllFilter>()
            SelectTypeSteel -> _state.select<SteelFilter>()
            SelectTypeWood -> _state.select<WoodFilter>()
        }
    }

    private fun updateSecondaryFilterQuery(action: SecondaryFilterAction) {
        when (action) {
            SelectSortByAlphabetical -> _sortByFilter.value = SortByFilter.Alphabetical
            SelectSortByDrop -> _sortByFilter.value = SortByFilter.Drop
            SelectSortByGForce -> _sortByFilter.value = SortByFilter.GForce
            SelectSortByHeight -> _sortByFilter.value = SortByFilter.Height
            SelectSortByInversions -> _sortByFilter.value = SortByFilter.Inversions
            SelectSortByLength -> _sortByFilter.value = SortByFilter.Length
            SelectSortByMaxVertical -> _sortByFilter.value = SortByFilter.MaxVertical
            SelectSortBySpeed -> _sortByFilter.value = SortByFilter.Speed
            SelectTypeAll -> _typeFilter.value = TypeFilter.All
            SelectTypeSteel -> _typeFilter.value = TypeFilter.Steel
            SelectTypeWood -> _typeFilter.value = TypeFilter.Wood
        }
    }
}