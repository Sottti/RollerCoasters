package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ExploreAction {
    @Immutable
    sealed interface PrimaryFilterAction : ExploreAction {

        @Immutable
        data object ShowSortFilters : PrimaryFilterAction

        @Immutable
        data object HideSortFilters : PrimaryFilterAction

        @Immutable
        data object ShowTypeFilters : PrimaryFilterAction

        @Immutable
        data object HideTypeFilters : PrimaryFilterAction
    }

    sealed interface SecondaryFilterAction : ExploreAction {
        @Immutable
        data object SelectSortByAlphabetical : SecondaryFilterAction

        @Immutable
        data object SelectSortByDrop : SecondaryFilterAction

        @Immutable
        data object SelectSortByGForce : SecondaryFilterAction

        @Immutable
        data object SelectSortByHeight : SecondaryFilterAction

        @Immutable
        data object SelectSortByInversions : SecondaryFilterAction

        @Immutable
        data object SelectSortByLength : SecondaryFilterAction

        @Immutable
        data object SelectSortByMaxVertical : SecondaryFilterAction

        @Immutable
        data object SelectSortBySpeed : SecondaryFilterAction

        @Immutable
        data object SelectTypeAll : SecondaryFilterAction

        @Immutable
        data object SelectTypeSteel : SecondaryFilterAction

        @Immutable
        data object SelectTypeWood : SecondaryFilterAction
    }
}
