package com.sottti.roller.coasters.presentation.explore.data

import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.TypePrimaryFilter

internal val ExploreState.sortByPrimary: SortByPrimaryFilter
    get() = filters.primary.filterIsInstance<SortByPrimaryFilter>().first()

internal val ExploreState.typePrimary: TypePrimaryFilter
    get() = filters.primary.filterIsInstance<TypePrimaryFilter>().first()

internal val ExploreState.dropFilter: DropFilter
    get() = filters.secondary.filterIsInstance<DropFilter>().first()

internal val ExploreState.alphabeticalFilter: AlphabeticalFilter
    get() = filters.secondary.filterIsInstance<AlphabeticalFilter>().first()

internal val ExploreState.allFilter: AllFilter
    get() = filters.secondary.filterIsInstance<AllFilter>().first()

internal val ExploreState.steelFilter: SteelFilter
    get() = filters.secondary.filterIsInstance<SteelFilter>().first()
