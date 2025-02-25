package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class ExploreAction {
    @Immutable
    sealed class PrimaryFilterAction : ExploreAction() {
        data object ShowTypeFilters : PrimaryFilterAction()
        data object HideTypeFilters : PrimaryFilterAction()
    }

    @Immutable
    sealed class SecondaryFilterAction : ExploreAction() {
        data object ShowTypeSteel : SecondaryFilterAction()
        data object ShowTypeWood : SecondaryFilterAction()
    }
}