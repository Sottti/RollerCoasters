package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByDrop
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByGForce
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByHeight
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByInversions
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByLength
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortByMaxVertical
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectSortBySpeed
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeSteel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction.SelectTypeWood
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter.SortBySecondaryFilter.SpeedFilter
import kotlinx.coroutines.flow.Flow

internal object ExploreInitialStates {
    internal fun state(
        rollerCoastersFlow: Flow<PagingData<ExploreRollerCoaster>>,
    ): ExploreState = ExploreState(
        rollerCoastersFlow = rollerCoastersFlow,
        filters = filters,
    )

    private val heightFilter: HeightFilter =
        HeightFilter(
            action = SelectSortByHeight,
            labelResId = R.string.chip_label_sort_by_height,
            selected = false,
            visible = false,
        )

    private val dropFilter: DropFilter =
        DropFilter(
            action = SelectSortByDrop,
            labelResId = R.string.chip_label_sort_by_drop,
            selected = false,
            visible = false,
        )

    private val inversionsFilter: InversionsFilter =
        InversionsFilter(
            action = SelectSortByInversions,
            labelResId = R.string.chip_label_sort_by_inversions,
            selected = false,
            visible = false,
        )

    private val lengthFilter: LengthFilter =
        LengthFilter(
            action = SelectSortByLength,
            labelResId = R.string.chip_label_sort_by_length,
            selected = false,
            visible = false,
        )

    private val maxVerticalFilter: MaxVerticalFilter =
        MaxVerticalFilter(
            action = SelectSortByMaxVertical,
            labelResId = R.string.chip_label_sort_by_max_vertical,
            selected = false,
            visible = false,
        )

    private val speedFilter: SpeedFilter =
        SpeedFilter(
            action = SelectSortBySpeed,
            labelResId = R.string.chip_label_sort_by_speed,
            selected = false,
            visible = false,
        )

    private val gForceFilter: GForceFilter =
        GForceFilter(
            action = SelectSortByGForce,
            labelResId = R.string.chip_label_sort_by_g_force,
            selected = false,
            visible = false,
        )

    private val woodFilter: SecondaryFilter.TypeSecondaryFilter.WoodFilter =
        SecondaryFilter.TypeSecondaryFilter.WoodFilter(
            action = SelectTypeWood,
            labelResId = R.string.chip_label_type_wood,
            selected = false,
            visible = false,
        )

    private val steelFilter: SecondaryFilter.TypeSecondaryFilter.SteelFilter =
        SecondaryFilter.TypeSecondaryFilter.SteelFilter(
            action = SelectTypeSteel,
            labelResId = R.string.chip_label_type_steel,
            selected = false,
            visible = false,
        )

    internal val sortByPrimaryFilter: PrimaryFilter.SortByPrimaryFilter =
        PrimaryFilter.SortByPrimaryFilter(
            action = ShowSortFilters,
            expanded = false,
            labelResId = R.string.chip_label_sort_by,
            selected = false,
        )

    internal val typePrimaryFilter: PrimaryFilter.TypePrimaryFilter =
        PrimaryFilter.TypePrimaryFilter(
            action = ShowTypeFilters,
            expanded = false,
            labelResId = R.string.chip_label_type,
            selected = false,
        )

    internal val sortBySecondaryFilters: List<SortBySecondaryFilter> =
        listOf(
            heightFilter,
            dropFilter,
            speedFilter,
            inversionsFilter,
            lengthFilter,
            maxVerticalFilter,
            gForceFilter,
        )

    internal val typeSecondaryFilters: List<SecondaryFilter.TypeSecondaryFilter> =
        listOf(
            steelFilter,
            woodFilter,
        )

    private val primaryFilters: List<PrimaryFilter> = listOf(sortByPrimaryFilter, typePrimaryFilter)

    internal val secondaryFilters: List<SecondaryFilter> =
        sortBySecondaryFilters + typeSecondaryFilters

    private val filters: Filters = Filters(
        primary = primaryFilters,
        secondary = secondaryFilters,
    )
}