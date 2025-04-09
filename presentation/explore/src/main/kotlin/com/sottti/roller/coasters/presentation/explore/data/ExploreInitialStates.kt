package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.explore.R
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
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
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
import kotlinx.coroutines.flow.Flow

internal fun initialState(
    rollerCoastersFlow: Flow<PagingData<ExploreRollerCoaster>>,
): ExploreState = ExploreState(
    rollerCoasters = rollerCoastersFlow,
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

internal fun sortByPrimaryFilterInitialState(): PrimaryFilter.SortByPrimaryFilter =
    PrimaryFilter.SortByPrimaryFilter(
        action = ShowSortFilters,
        expanded = false,
        labelResId = R.string.chip_label_sort_by_alphabetical,
        leadingIcon = Icons.Sort.Filled,
        selected = true,
    )

internal fun typePrimaryFilterInitialState(): PrimaryFilter.TypePrimaryFilter =
    PrimaryFilter.TypePrimaryFilter(
        action = ShowTypeFilters,
        expanded = false,
        labelResId = R.string.chip_label_type_all,
        selected = true,
        leadingIcon = Icons.FilterList.Filled,
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

private fun typeSecondaryFiltersInitialState(): List<SecondaryFilter.TypeSecondaryFilter> =
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
        leadingIcon = Icons.CheckSmall.Filled,
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

private val allFilter: SecondaryFilter.TypeSecondaryFilter.AllFilter =
    SecondaryFilter.TypeSecondaryFilter.AllFilter(
        action = SelectTypeAll,
        labelResId = R.string.chip_label_type_all,
        selected = true,
        visible = false,
        leadingIcon = Icons.CheckSmall.Filled,
    )

private val steelFilter: SecondaryFilter.TypeSecondaryFilter.SteelFilter =
    SecondaryFilter.TypeSecondaryFilter.SteelFilter(
        action = SelectTypeSteel,
        labelResId = R.string.chip_label_type_steel,
        leadingIcon = null,
        selected = false,
        visible = false,
    )

private val woodFilter: SecondaryFilter.TypeSecondaryFilter.WoodFilter =
    SecondaryFilter.TypeSecondaryFilter.WoodFilter(
        action = SelectTypeWood,
        labelResId = R.string.chip_label_type_wood,
        selected = false,
        visible = false,
        leadingIcon = null,
    )