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
        data object ClearSortHeight : SecondaryFilterAction()
        data object ClearTypeSteel : SecondaryFilterAction()
        data object ClearTypeWood : SecondaryFilterAction()
        data object SelectSortHeight : SecondaryFilterAction()
        data object SelectTypeSteel : SecondaryFilterAction()
        data object SelectTypeWood : SecondaryFilterAction()
    }
}