package com.sottti.roller.coasters.presentation.explore.model

internal sealed interface ExploreEvent {
    object ScrollToTop : ExploreEvent
}