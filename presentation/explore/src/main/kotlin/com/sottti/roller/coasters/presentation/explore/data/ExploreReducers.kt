package com.sottti.roller.coasters.presentation.explore.data

import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearSortBySecondaryFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.ClearTypeSecondaryFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.TypePrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.WoodFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.reflect.KClass

internal fun MutableStateFlow<ExploreState>.expandSortByPrimaryFilter() {
    collapseTypePrimaryFilter()
        .show<SortBySecondaryFilter>()
        .update { state ->
            state.copy(
                filters = state.filters.copy(
                    primary = state.filters.primary.map { filter ->
                        when (filter) {
                            is TypePrimaryFilter -> filter
                            is SortByPrimaryFilter -> filter.copy(
                                labelResId = R.string.chip_label_sort_by,
                                action = HideSortFilters,
                                expanded = true,
                            )
                        }
                    },
                )
            )
        }
}

internal fun MutableStateFlow<ExploreState>.expandTypePrimaryFilter() {
    collapseSortByPrimaryFilter()
        .show<TypeSecondaryFilter>()
        .update { state ->
            state.copy(
                filters = state.filters.copy(
                    primary = state.filters.primary.map { filter ->
                        when (filter) {
                            is SortByPrimaryFilter -> filter
                            is TypePrimaryFilter -> filter.copy(
                                labelResId = R.string.chip_label_type,
                                action = HideTypeFilters,
                                expanded = true,
                            )
                        }
                    },
                )
            )
        }
}

internal fun MutableStateFlow<ExploreState>.collapseSortByPrimaryFilter() =
    collapsePrimaryFilter(SortByPrimaryFilter::class) { state ->
        copy(
            labelResId = state.labelForCollapsed(this),
            action = ShowSortFilters,
            expanded = false
        )
    }

internal fun MutableStateFlow<ExploreState>.collapseTypePrimaryFilter() =
    collapsePrimaryFilter(TypePrimaryFilter::class) { state ->
        copy(
            labelResId = state.labelForCollapsed(this),
            action = ShowTypeFilters,
            expanded = false
        )
    }

@Suppress("UNCHECKED_CAST")
private fun <T : PrimaryFilter> MutableStateFlow<ExploreState>.collapsePrimaryFilter(
    primaryFilterClass: KClass<T>,
    updateFilter: T.(ExploreState) -> T,
) = apply {
    hideSecondaryFilters()
        .update { state ->
            state.copy(
                filters = state.filters.copy(
                    primary = state.filters.primary.map { filter ->
                        when {
                            primaryFilterClass.isInstance(filter) -> (filter as T).updateFilter(
                                state
                            )

                            else -> filter
                        }
                    }
                )
            )
        }
}

internal fun MutableStateFlow<ExploreState>.selectSortByHeight() =
    selectSortBy(HeightFilter::class, R.string.chip_label_sort_by_height)

internal fun MutableStateFlow<ExploreState>.selectSortByDrop() =
    selectSortBy(DropFilter::class, R.string.chip_label_sort_by_drop)

internal fun MutableStateFlow<ExploreState>.selectSortByGForce() =
    selectSortBy(GForceFilter::class, R.string.chip_label_sort_by_g_force)

internal fun MutableStateFlow<ExploreState>.selectSortByInversions() =
    selectSortBy(InversionsFilter::class, R.string.chip_label_sort_by_inversions)

internal fun MutableStateFlow<ExploreState>.selectSortByLength() =
    selectSortBy(LengthFilter::class, R.string.chip_label_sort_by_length)

internal fun MutableStateFlow<ExploreState>.selectSortByMaxVertical() =
    selectSortBy(MaxVerticalFilter::class, R.string.chip_label_sort_by_max_vertical)

internal fun MutableStateFlow<ExploreState>.selectSortBySpeed() =
    selectSortBy(SpeedFilter::class, R.string.chip_label_sort_by_speed)

internal fun MutableStateFlow<ExploreState>.selectTypeSteel() =
    selectType(SteelFilter::class, R.string.chip_label_type_steel)

internal fun MutableStateFlow<ExploreState>.selectTypeWood() =
    selectType(WoodFilter::class, R.string.chip_label_type_wood)


internal fun <T : SortBySecondaryFilter> MutableStateFlow<ExploreState>.selectSortBy(
    filterClass: KClass<T>,
    labelResId: Int
) = clearSortByFilters()
    .update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is SortByPrimaryFilter -> filter.copy(
                            labelResId = labelResId,
                            action = ShowSortFilters,
                            selected = true,
                            expanded = false,
                        )

                        else -> filter
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when {
                        filterClass.isInstance(filter) -> filter.selectFilter()
                        else -> filter
                    }
                }
            )
        )
    }

internal fun <T : TypeSecondaryFilter> MutableStateFlow<ExploreState>.selectType(
    filterClass: KClass<T>,
    labelResId: Int
) = clearTypeFilters()
    .update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when (filter) {
                        is TypePrimaryFilter -> filter.copy(
                            labelResId = labelResId,
                            action = ShowTypeFilters,
                            selected = true,
                            expanded = false,
                        )

                        else -> filter
                    }
                },
                secondary = state.filters.secondary.map { filter ->
                    when {
                        filterClass.isInstance(filter) -> filter.selectFilter()
                        else -> filter
                    }
                }
            )
        )
    }

private fun SecondaryFilter.selectFilter(): SecondaryFilter = when (this) {
    is DropFilter -> copy(action = ClearSortBySecondaryFilters, selected = true, visible = false)
    is GForceFilter -> copy(action = ClearSortBySecondaryFilters, selected = true, visible = false)
    is HeightFilter -> copy(action = ClearSortBySecondaryFilters, selected = true, visible = false)
    is InversionsFilter -> copy(
        action = ClearSortBySecondaryFilters,
        selected = true,
        visible = false
    )

    is LengthFilter -> copy(action = ClearSortBySecondaryFilters, selected = true, visible = false)
    is MaxVerticalFilter -> copy(
        action = ClearSortBySecondaryFilters,
        selected = true,
        visible = false
    )

    is SpeedFilter -> copy(action = ClearSortBySecondaryFilters, selected = true, visible = false)
    is SteelFilter -> copy(action = ClearTypeSecondaryFilters, selected = true, visible = false)
    is WoodFilter -> copy(action = ClearTypeSecondaryFilters, selected = true, visible = false)
}

private inline fun <reified T : SecondaryFilter> MutableStateFlow<ExploreState>.show() = apply {
    update { state ->
        state.copy(
            filters = state.filters.copy(
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is DropFilter -> filter.copy(visible = filter is T)
                        is GForceFilter -> filter.copy(visible = filter is T)
                        is HeightFilter -> filter.copy(visible = filter is T)
                        is InversionsFilter -> filter.copy(visible = filter is T)
                        is LengthFilter -> filter.copy(visible = filter is T)
                        is MaxVerticalFilter -> filter.copy(visible = filter is T)
                        is SpeedFilter -> filter.copy(visible = filter is T)
                        is SteelFilter -> filter.copy(visible = filter is T)
                        is WoodFilter -> filter.copy(visible = filter is T)
                    }
                })
        )
    }
}

private fun MutableStateFlow<ExploreState>.hideSecondaryFilters() = apply {
    update { state ->
        state.copy(
            filters = state.filters.copy(
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is DropFilter -> filter.copy(visible = false)
                        is GForceFilter -> filter.copy(visible = false)
                        is HeightFilter -> filter.copy(visible = false)
                        is InversionsFilter -> filter.copy(visible = false)
                        is LengthFilter -> filter.copy(visible = false)
                        is MaxVerticalFilter -> filter.copy(visible = false)
                        is SpeedFilter -> filter.copy(visible = false)
                        is SteelFilter -> filter.copy(visible = false)
                        is WoodFilter -> filter.copy(visible = false)
                    }
                },
            )
        )
    }
}

internal fun MutableStateFlow<ExploreState>.clearSortByFilters() =
    clearFilters(primaryFilter = SortByPrimaryFilter::class)

internal fun MutableStateFlow<ExploreState>.clearTypeFilters() =
    clearFilters(primaryFilter = TypePrimaryFilter::class)

private fun <T : PrimaryFilter> MutableStateFlow<ExploreState>.clearFilters(
    primaryFilter: KClass<T>,
) = apply {
    update { state ->
        val secondaryFilterClass = state.filters.primary
            .firstOrNull { primaryFilter.isInstance(it) }
            ?.secondaryFilterClass

        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    if (primaryFilter.isInstance(filter)) initialStateFor(filter) else filter
                },
                secondary = state.filters.secondary
                    .filterNot { filter -> secondaryFilterClass?.isInstance(filter) == true } +
                        ExploreInitialStates.secondaryFilters
                            .filter { filter -> secondaryFilterClass?.isInstance(filter) == true }
            )
        )
    }
}

internal fun ExploreState.labelForCollapsed(filter: PrimaryFilter) =
    filters
        .secondary
        .firstOrNull { it.selected && filter.secondaryFilterClass.isInstance(it) }
        ?.labelResId
        ?: defaultLabelResIdFor(filter)

private val PrimaryFilter.secondaryFilterClass: KClass<out SecondaryFilter>
    get() = when (this) {
        is SortByPrimaryFilter -> SortBySecondaryFilter::class
        is TypePrimaryFilter -> TypeSecondaryFilter::class
    }

private fun defaultLabelResIdFor(filter: PrimaryFilter) =
    when (filter) {
        is SortByPrimaryFilter -> R.string.chip_label_sort_by
        is TypePrimaryFilter -> R.string.chip_label_type
    }

private fun initialStateFor(filter: PrimaryFilter) = when (filter) {
    is SortByPrimaryFilter -> ExploreInitialStates.sortByPrimaryFilter
    is TypePrimaryFilter -> ExploreInitialStates.typePrimaryFilter
}