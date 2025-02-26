package com.sottti.roller.coasters.presentation.explore.data

import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearSortHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.TypePrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<ExploreState>.showSortByFilters() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            labelResId = R.string.chip_label_sort_by,
                            action = HideSortFilters,
                            expanded = true,
                        )

                        is TypePrimaryFilter -> filter.copy(
                            action = ShowTypeFilters,
                            expanded = false,
                        )
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(visible = true)
                        is TypeSecondaryFilter.SteelFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.WoodFilter -> filter.copy(visible = false)
                    }
                },
            )
        )
    }

internal fun MutableStateFlow<ExploreState>.hideSortFilters() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            labelResId = state
                                .filters
                                .secondary
                                .filter { it is SortBySecondaryFilter }
                                .firstOrNull { it.selected }
                                ?.labelResId ?: R.string.chip_label_sort_by,
                            action = ShowSortFilters,
                            expanded = false,
                        )

                        is TypePrimaryFilter -> filter
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.SteelFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.WoodFilter -> filter.copy(visible = false)
                    }
                },
            )
        )
    }

internal fun MutableStateFlow<ExploreState>.showTypeFilters() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            action = ShowSortFilters,
                            expanded = false,
                        )

                        is TypePrimaryFilter -> filter.copy(
                            action = HideTypeFilters,
                            expanded = true
                        )
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.SteelFilter -> filter.copy(visible = true)
                        is TypeSecondaryFilter.WoodFilter -> filter.copy(visible = true)
                    }
                },
            )
        )
    }

internal fun MutableStateFlow<ExploreState>.hideTypeFilters() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter
                        is TypePrimaryFilter -> filter.copy(
                            action = ShowTypeFilters,
                            expanded = false
                        )
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.SteelFilter -> filter.copy(visible = false)
                        is TypeSecondaryFilter.WoodFilter -> filter.copy(visible = false)
                    }
                },
            )
        )
    }

internal fun MutableStateFlow<ExploreState>.selectSortByHeight() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            labelResId = R.string.chip_label_sort_by_height,
                            action = ShowSortFilters,
                            selected = true,
                            expanded = false,
                        )

                        is TypePrimaryFilter -> filter
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(
                            action = ClearSortHeight,
                            selected = true,
                            visible = false,
                        )

                        is TypeSecondaryFilter.SteelFilter -> filter
                        is TypeSecondaryFilter.WoodFilter -> filter
                    }
                }
            )
        )
    }

internal fun MutableStateFlow<ExploreState>.clearSortByHeight() =
    update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            labelResId = R.string.chip_label_sort_by,
                            action = ShowSortFilters,
                            selected = false,
                            expanded = false,
                        )

                        is TypePrimaryFilter -> filter
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is SortBySecondaryFilter.HeightFilter -> filter.copy(
                            action = SelectSortHeight,
                            selected = false,
                            visible = false,
                        )

                        is TypeSecondaryFilter.SteelFilter -> filter
                        is TypeSecondaryFilter.WoodFilter -> filter
                    }
                }
            )
        )
    }