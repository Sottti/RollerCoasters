package com.sottti.roller.coasters.presentation.explore.data

import co.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter.TypePrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.AllFilter
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

private inline fun <reified T : SecondaryFilter> MutableStateFlow<ExploreState>.collapsePrimaryFilter() =
    apply {
        when {
            SortBySecondaryFilter::class.java.isAssignableFrom(T::class.java) ->
                collapseSortByPrimaryFilter()

            TypeSecondaryFilter::class.java.isAssignableFrom(T::class.java) ->
                collapseTypePrimaryFilter()

            else -> throw IllegalArgumentException("Unknown filter type: ${T::class}")
        }
    }

internal fun MutableStateFlow<ExploreState>.collapseSortByPrimaryFilter() =
    collapsePrimaryFilter(SortByPrimaryFilter::class) { state ->
        copy(
            action = ShowSortFilters,
            expanded = false,
            labelResId = state.collapsedLabel(this),
        )
    }

internal fun MutableStateFlow<ExploreState>.collapseTypePrimaryFilter() =
    collapsePrimaryFilter(TypePrimaryFilter::class) { state ->
        copy(
            action = ShowTypeFilters,
            expanded = false,
            labelResId = state.collapsedLabel(this),
        )
    }

@Suppress("UNCHECKED_CAST")
private fun <T : PrimaryFilter> MutableStateFlow<ExploreState>.collapsePrimaryFilter(
    primaryFilterClass: KClass<T>,
    updateFilter: T.(ExploreState) -> T,
) = apply {
    hideSecondaryFilters().update { state ->
        state.copy(
            filters = state.filters.copy(
                primary = state.filters.primary.map { filter ->
                    when {
                        primaryFilterClass.isInstance(filter) -> (filter as T).updateFilter(state)
                        else -> filter
                    }
                })
        )
    }
}

internal inline fun <reified T : SecondaryFilter> MutableStateFlow<ExploreState>.select() =
    selectSecondaryFilter<T>()
        .collapsePrimaryFilter<T>()

private inline fun <reified T : SecondaryFilter> MutableStateFlow<ExploreState>.selectSecondaryFilter() =
    apply {
        update { state ->
            state.copy(
                filters = state.filters.copy(
                    secondary = state.filters.secondary.map { filter ->
                        when {
                            filter is T -> filter.select()
                            filter.secondaryFilterClass.java.isAssignableFrom(T::class.java) ->
                                filter.unselect()

                            else -> filter
                        }
                    }
                )
            )
        }
    }

private fun SecondaryFilter.select(): SecondaryFilter = when (this) {
    is AlphabeticalFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is DropFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is GForceFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is HeightFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is InversionsFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is LengthFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is MaxVerticalFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is SpeedFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is AllFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is SteelFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
    is WoodFilter -> copy(leadingIcon = Icons.CheckSmall.filled, selected = true)
}

private fun SecondaryFilter.unselect(): SecondaryFilter = when (this) {
    is AllFilter -> copy(leadingIcon = null, selected = false)
    is AlphabeticalFilter -> copy(leadingIcon = null, selected = false)
    is DropFilter -> copy(leadingIcon = null, selected = false)
    is GForceFilter -> copy(leadingIcon = null, selected = false)
    is HeightFilter -> copy(leadingIcon = null, selected = false)
    is InversionsFilter -> copy(leadingIcon = null, selected = false)
    is LengthFilter -> copy(leadingIcon = null, selected = false)
    is MaxVerticalFilter -> copy(leadingIcon = null, selected = false)
    is SpeedFilter -> copy(leadingIcon = null, selected = false)
    is SteelFilter -> copy(leadingIcon = null, selected = false)
    is WoodFilter -> copy(leadingIcon = null, selected = false)
}

private inline fun <reified T : SecondaryFilter> MutableStateFlow<ExploreState>.show() = apply {
    update { state ->
        state.copy(
            filters = state.filters.copy(
                secondary = state.filters.secondary.map { filter ->
                    when (filter) {
                        is AllFilter -> filter.copy(visible = filter is T)
                        is AlphabeticalFilter -> filter.copy(visible = filter is T)
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
                        is AllFilter -> filter.copy(visible = false)
                        is AlphabeticalFilter -> filter.copy(visible = false)
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

private fun PrimaryFilter.defaultLabel() = when (this) {
    is SortByPrimaryFilter -> R.string.chip_label_sort_by
    is TypePrimaryFilter -> R.string.chip_label_type
}

private fun ExploreState.collapsedLabel(filter: PrimaryFilter) =
    filters
        .secondary
        .firstOrNull { it.selected && filter.secondaryFilterClass.isInstance(it) }
        ?.labelResId
        ?: filter.defaultLabel()

private val PrimaryFilter.secondaryFilterClass: KClass<out SecondaryFilter>
    get() = when (this) {
        is SortByPrimaryFilter -> SortBySecondaryFilter::class
        is TypePrimaryFilter -> TypeSecondaryFilter::class
    }

private val SecondaryFilter.secondaryFilterClass: KClass<out SecondaryFilter>
    get() = when (this) {
        is AlphabeticalFilter,
        is DropFilter,
        is GForceFilter,
        is HeightFilter,
        is InversionsFilter,
        is LengthFilter,
        is MaxVerticalFilter,
        is SpeedFilter,
            -> SortBySecondaryFilter::class

        is AllFilter,
        is SteelFilter,
        is WoodFilter,
            -> TypeSecondaryFilter::class
    }