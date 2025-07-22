package com.sottti.roller.coasters.presentation.search.model

internal data class SearchState(
    val searchBar: SearchBarState,
    val isLoading: Boolean = false,
    val results: List<SearchResult> = emptyList(),
)

internal data class SearchBarState(
    val hint: String,
    val query: String = "",
)