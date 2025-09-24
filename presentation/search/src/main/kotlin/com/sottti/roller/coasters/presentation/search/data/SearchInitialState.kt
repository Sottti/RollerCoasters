package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchState

internal val initialState = SearchState(
    searchBar = searchBarInitialState(),
    searchResult = searchResultsEmpty(),
)

private fun searchBarInitialState(): SearchBarState =
    SearchBarState(
        hint = R.string.search_hint,
        loading = false,
        query = null,
        showClearIcon = false,
    )
