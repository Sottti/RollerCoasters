package com.sottti.roller.coasters.presentation.explore.model

internal sealed class ExploreAction {
    data class ShowRollerCoasterDetails(val rollerCoasterId: String) : ExploreAction()
    sealed class PrimaryFilterAction : ExploreAction() {
        data object ShowSortFilters : PrimaryFilterAction()
        data object HideSortFilters : PrimaryFilterAction()
        data object ShowTypeFilters : PrimaryFilterAction()
        data object HideTypeFilters : PrimaryFilterAction()
    }

    sealed class SecondaryFilterAction : ExploreAction() {
        data object SelectSortByAlphabetical : SecondaryFilterAction()
        data object SelectSortByDrop : SecondaryFilterAction()
        data object SelectSortByGForce : SecondaryFilterAction()
        data object SelectSortByHeight : SecondaryFilterAction()
        data object SelectSortByInversions : SecondaryFilterAction()
        data object SelectSortByLength : SecondaryFilterAction()
        data object SelectSortByMaxVertical : SecondaryFilterAction()
        data object SelectSortBySpeed : SecondaryFilterAction()
        data object SelectTypeAll : SecondaryFilterAction()
        data object SelectTypeSteel : SecondaryFilterAction()
        data object SelectTypeWood : SecondaryFilterAction()
    }
}