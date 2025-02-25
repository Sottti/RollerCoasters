package com.sottti.roller.coasters.presentation.explore.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ShowTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ShowTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.Filter.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.Filter.SecondaryFilter.Steel
import com.sottti.roller.coasters.presentation.explore.model.Filter.SecondaryFilter.Wood
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.RollerCoasterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    rollerCoastersRepository: RollerCoastersRepository,
) : ViewModel() {

    private val _rollerCoastersFlow: Flow<PagingData<RollerCoasterUiModel>> =
        rollerCoastersRepository.getRollerCoastersSortedByHeight().toUiModel()
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(
        ExploreState(
            rollerCoastersFlow = _rollerCoastersFlow,
            filters = filters()
        )
    )

    val state: StateFlow<ExploreState> = _state.asStateFlow()

    internal val onAction: (ExploreAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: ExploreAction) {
        when (action) {
            is ExploreAction.PrimaryFilterAction -> processPrimaryFilterAction(action)
            is ExploreAction.SecondaryFilterAction -> processSecondaryFilterAction(action)
        }
    }

    private fun processPrimaryFilterAction(action: ExploreAction.PrimaryFilterAction) {
        when (action) {
            HideTypeFilters -> _state.update {
                it.copy(
                    filters = it.filters.copy(
                        primaryFilters = it.filters.primaryFilters.map { filter ->
                            when (filter) {
                                is PrimaryFilter.TypeFilter -> filter.copy(
                                    expanded = !filter.expanded,
                                    action = ShowTypeFilters,
                                )
                            }
                        },
                        secondaryFilters = null,
                    )
                )
            }

            ShowTypeFilters -> _state.update {
                it.copy(
                    filters = it.filters.copy(
                        primaryFilters = it.filters.primaryFilters.map { filter ->
                            when (filter) {
                                is PrimaryFilter.TypeFilter -> filter.copy(
                                    expanded = !filter.expanded,
                                    action = HideTypeFilters,
                                )
                            }
                        },
                        secondaryFilters = listOf(
                            Steel(
                                action = ShowTypeSteel,
                                labelResId = R.string.chip_label_type_steel,
                                selected = false,
                            ),
                            Wood(
                                action = ShowTypeWood,
                                labelResId = R.string.chip_label_type_wood,
                                selected = false,
                            ),
                        ),
                    )
                )
            }
        }

    }

    private fun processSecondaryFilterAction(action: ExploreAction.SecondaryFilterAction) {}
}

private fun filters(): Filters = Filters(
    primaryFilters = primaryFilterTypeFilters(),
    secondaryFilters = null,
)

private fun primaryFilterTypeFilters(): List<PrimaryFilter.TypeFilter> = listOf(
    PrimaryFilter.TypeFilter(
        action = ShowTypeFilters,
        expanded = false,
        labelResId = R.string.chip_label_type,
        selected = false,
    ),
)