package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchResults
import com.sottti.roller.coasters.presentation.search.model.SearchState

internal val initialState = SearchState(
    loading = true,
    searchBar = searchBarInitialState(),
    searchResults = searchResultsInitialState(),
)

private fun searchResultsInitialState(): SearchResults.Empty = SearchResults.Empty(
    primaryText = R.string.search_empty_primary_text,
    secondaryText = R.string.search_empty_secondary_text,
)

private fun searchBarInitialState(): SearchBarState = SearchBarState(
    hint = R.string.search_hint,
    query = null,
    showClearIcon = false,
)