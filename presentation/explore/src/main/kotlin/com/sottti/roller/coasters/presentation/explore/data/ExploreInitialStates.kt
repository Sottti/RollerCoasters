package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.TypeSecondaryFilter.WoodFilter
import kotlinx.coroutines.flow.Flow

internal fun initialState(
    rollerCoastersFlow: Flow<PagingData<ExploreRollerCoaster>>,
) = ExploreState(
    rollerCoastersFlow = rollerCoastersFlow,
    filters = filters(),
)

private fun filters(): Filters =
    Filters(
        primary = primaryFilters(),
        secondary = secondaryFilters(),
    )

private fun primaryFilters(): List<PrimaryFilter> =
    listOf(
        sortByPrimaryFilter(),
        typePrimaryFilter(),
    )

private fun sortByPrimaryFilter() =
    PrimaryFilter.SortByPrimaryFilter(
        action = ShowSortFilters,
        expanded = false,
        labelResId = R.string.chip_label_sort_by,
        selected = false,
    )

private fun typePrimaryFilter(): PrimaryFilter.TypePrimaryFilter =
    PrimaryFilter.TypePrimaryFilter(
        action = ShowTypeFilters,
        expanded = false,
        labelResId = R.string.chip_label_type,
        selected = false,
    )

private fun secondaryFilters() = sortByFilters() + typeFilters()

private fun sortByFilters() =
    listOf(
        heightFilter(),
    )

private fun typeFilters() =
    listOf(
        steelFilter(),
        woodFilter(),
    )

private fun heightFilter(): HeightFilter = HeightFilter(
    action = SelectSortHeight,
    labelResId = R.string.chip_label_sort_by_height,
    selected = false,
    visible = false,
)

private fun woodFilter(): WoodFilter = WoodFilter(
    action = SelectTypeWood,
    labelResId = R.string.chip_label_type_wood,
    selected = false,
    visible = false,
)

private fun steelFilter() =
    SteelFilter(
        action = SelectTypeSteel,
        labelResId = R.string.chip_label_type_steel,
        selected = false,
        visible = false,
    )