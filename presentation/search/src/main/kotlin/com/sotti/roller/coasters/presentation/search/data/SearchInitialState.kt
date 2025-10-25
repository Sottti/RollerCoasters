package com.sotti.roller.coasters.presentation.search.data

import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.presentation.search.R
import com.sotti.roller.coasters.presentation.search.model.SearchBarState
import com.sotti.roller.coasters.presentation.search.model.SearchState

internal val initialState = SearchState(
    searchBar = searchBarInitialState(),
    searchResult = emptyList<RollerCoaster>().toSearchResult(),
)

private fun searchBarInitialState(): SearchBarState =
    SearchBarState(
        hint = R.string.search_hint,
        loading = false,
        query = null,
    )
