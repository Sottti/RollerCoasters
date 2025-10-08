package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoasters
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
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
import com.sottti.roller.coasters.presentation.explore.model.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.WoodFilter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    observeAppLanguage: ObserveAppLanguage,
    observeSystemLocale: ObserveSystemLocale,
    observeRollerCoasters: ObserveRollerCoasters,
    stringProvider: StringProvider,
    displayUnitFormatter: DisplayUnitFormatter,
) : ViewModel() {

    private val sortByFilter = MutableStateFlow(SortByFilter.Alphabetical)
    private val typeFilter = MutableStateFlow(TypeFilter.All)

    @OptIn(ExperimentalCoroutinesApi::class)
    val rollerCoasters: Flow<PagingData<ExploreRollerCoaster>> =
        combine(
            flow = sortByFilter,
            flow2 = typeFilter,
            flow3 = observeAppLanguage(),
            flow4 = observeSystemLocale()
        ) { sortByFilter, typeFilter, appLanguage, systemLocale ->
            observeRollerCoasters(
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            ).toUiModel(
                appLanguage = appLanguage,
                sortByFilter = sortByFilter,
                stringProvider = stringProvider,
                systemLocale = systemLocale,
                displayUnitFormatter = displayUnitFormatter,
            )
        }.onEach { _events.tryEmit(ExploreEvent.ScrollToTop) }
            .flatMapLatest { it }
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<ExploreState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<ExploreEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

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
            SelectSortByAlphabetical -> {
                _state.select<AlphabeticalFilter>()
                sortByFilter.value = SortByFilter.Alphabetical
            }

            SelectSortByDrop -> {
                _state.select<DropFilter>()
                sortByFilter.value = SortByFilter.Drop
            }

            SelectSortByGForce -> {
                _state.select<GForceFilter>()
                sortByFilter.value = SortByFilter.GForce
            }

            SelectSortByHeight -> {
                _state.select<HeightFilter>()
                sortByFilter.value = SortByFilter.Height
            }

            SelectSortByInversions -> {
                _state.select<InversionsFilter>()
                sortByFilter.value = SortByFilter.Inversions
            }

            SelectSortByLength -> {
                _state.select<LengthFilter>()
                sortByFilter.value = SortByFilter.Length
            }

            SelectSortByMaxVertical -> {
                _state.select<MaxVerticalFilter>()
                sortByFilter.value = SortByFilter.MaxVertical
            }

            SelectSortBySpeed -> {
                _state.select<SpeedFilter>()
                sortByFilter.value = SortByFilter.Speed
            }

            SelectTypeAll -> {
                _state.select<AllFilter>()
                typeFilter.value = TypeFilter.All
            }

            SelectTypeSteel -> {
                _state.select<SteelFilter>()
                typeFilter.value = TypeFilter.Steel
            }

            SelectTypeWood -> {
                _state.select<WoodFilter>()
                typeFilter.value = TypeFilter.Wood
            }
        }
    }
}
