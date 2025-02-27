package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class ExploreAction {
    @Immutable
    sealed class PrimaryFilterAction : ExploreAction() {
        data object HideSortFilters : PrimaryFilterAction()
        data object HideTypeFilters : PrimaryFilterAction()
        data object ShowSortFilters : PrimaryFilterAction()
        data object ShowTypeFilters : PrimaryFilterAction()
    }

    @Immutable
    sealed class SecondaryFilterAction : ExploreAction() {
        data object ClearSortBySecondaryFilters : SecondaryFilterAction()
        data object ClearTypeSecondaryFilters : SecondaryFilterAction()
        data object SelectSortByDrop : SecondaryFilterAction()
        data object SelectSortByGForce : SecondaryFilterAction()
        data object SelectSortByHeight : SecondaryFilterAction()
        data object SelectSortByInversions : SecondaryFilterAction()
        data object SelectSortByLength : SecondaryFilterAction()
        data object SelectSortByMaxVertical : SecondaryFilterAction()
        data object SelectSortBySpeed : SecondaryFilterAction()
        data object SelectTypeSteel : SecondaryFilterAction()
        data object SelectTypeWood : SecondaryFilterAction()
    }
}