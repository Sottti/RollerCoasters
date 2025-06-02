package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ExploreEvent {
    @Immutable
    object ScrollToTop : ExploreEvent
}