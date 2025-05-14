package com.sottti.roller.coasters.presentation.search.model

internal data class SearchState(
    val searchBar: SearchBarState,
)

internal data class SearchBarState(
    val hing: String,
)