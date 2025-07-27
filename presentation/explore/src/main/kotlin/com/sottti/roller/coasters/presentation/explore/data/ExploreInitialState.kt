package com.sottti.roller.coasters.presentation.explore.data

import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
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
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.TypePrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.TypeSecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.WoodFilter

internal fun initialState(
): ExploreState = ExploreState(
    filters = filtersInitialState(),
)

internal fun filtersInitialState(): Filters =
    Filters(
        primary = primaryFiltersInitialState(),
        secondary = secondaryFiltersInitialState(),
    )

private fun primaryFiltersInitialState(): List<PrimaryFilter> =
    listOf(
        sortByPrimaryFilterInitialState(),
        typePrimaryFilterInitialState(),
    )

internal fun sortByPrimaryFilterInitialState(): SortByPrimaryFilter =
    SortByPrimaryFilter(
        action = ShowSortFilters,
        expanded = false,
        labelResId = R.string.chip_label_sort_by_alphabetical,
        leadingIcon = Icons.Sort.filled,
        selected = true,
    )

internal fun typePrimaryFilterInitialState(): TypePrimaryFilter =
    TypePrimaryFilter(
        action = ShowTypeFilters,
        expanded = false,
        labelResId = R.string.chip_label_type_all,
        selected = true,
        leadingIcon = Icons.FilterList.filled,
    )

internal fun secondaryFiltersInitialState(): List<SecondaryFilter> =
    sortBySecondaryFiltersInitialState() + typeSecondaryFiltersInitialState()

private fun sortBySecondaryFiltersInitialState(): List<SortBySecondaryFilter> =
    listOf(
        alphabetical,
        heightFilter,
        speedFilter,
        inversionsFilter,
        dropFilter,
        lengthFilter,
        maxVerticalFilter,
        gForceFilter,
    )

private fun typeSecondaryFiltersInitialState(): List<TypeSecondaryFilter> =
    listOf(
        allFilter,
        steelFilter,
        woodFilter,
    )


private val alphabetical: AlphabeticalFilter =
    AlphabeticalFilter(
        action = SelectSortByAlphabetical,
        labelResId = R.string.chip_label_sort_by_alphabetical,
        selected = true,
        visible = false,
        leadingIcon = Icons.CheckSmall.filled,
    )

private val heightFilter: HeightFilter =
    HeightFilter(
        action = SelectSortByHeight,
        labelResId = R.string.chip_label_sort_by_height,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val dropFilter: DropFilter =
    DropFilter(
        action = SelectSortByDrop,
        labelResId = R.string.chip_label_sort_by_drop,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val inversionsFilter: InversionsFilter =
    InversionsFilter(
        action = SelectSortByInversions,
        labelResId = R.string.chip_label_sort_by_inversions,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val lengthFilter: LengthFilter =
    LengthFilter(
        action = SelectSortByLength,
        labelResId = R.string.chip_label_sort_by_length,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val maxVerticalFilter: MaxVerticalFilter =
    MaxVerticalFilter(
        action = SelectSortByMaxVertical,
        labelResId = R.string.chip_label_sort_by_max_vertical,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val speedFilter: SpeedFilter =
    SpeedFilter(
        action = SelectSortBySpeed,
        labelResId = R.string.chip_label_sort_by_speed,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val gForceFilter: GForceFilter =
    GForceFilter(
        action = SelectSortByGForce,
        labelResId = R.string.chip_label_sort_by_g_force,
        selected = false,
        visible = false,
        leadingIcon = null,
    )

private val allFilter: AllFilter =
    AllFilter(
        action = SelectTypeAll,
        labelResId = R.string.chip_label_type_all,
        selected = true,
        visible = false,
        leadingIcon = Icons.CheckSmall.filled,
    )

private val steelFilter: SteelFilter =
    SteelFilter(
        action = SelectTypeSteel,
        labelResId = R.string.chip_label_type_steel,
        leadingIcon = null,
        selected = false,
        visible = false,
    )

private val woodFilter: WoodFilter =
    WoodFilter(
        action = SelectTypeWood,
        labelResId = R.string.chip_label_type_wood,
        selected = false,
        visible = false,
        leadingIcon = null,
    )
