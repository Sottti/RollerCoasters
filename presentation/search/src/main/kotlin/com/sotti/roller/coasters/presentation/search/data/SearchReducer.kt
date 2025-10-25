package com.sotti.roller.coasters.presentation.search.data

import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.presentation.search.R
import com.sotti.roller.coasters.presentation.search.model.SearchResult
import com.sotti.roller.coasters.presentation.search.model.SearchState
import com.sotti.roller.coasters.presentation.search.model.toState

internal fun SearchState.updateQuery(query: String?): SearchState =
    copy(searchBar = searchBar.copy(query = query))

internal fun SearchState.updateLoading(loading: Boolean): SearchState =
    copy(searchBar = searchBar.copy(loading = loading))


internal fun SearchState.updateResults(
    results: List<RollerCoaster>,
): SearchState = copy(searchResult = results.toSearchResult())

internal fun List<RollerCoaster>.toSearchResult(): SearchResult =
    when {
        isEmpty() -> searchResultEmpty()
        else -> SearchResult.NotEmpty(rollerCoasters = this.toState())
    }

private fun searchResultEmpty(): SearchResult.Empty = SearchResult.Empty(
    primaryText = R.string.search_empty_primary_text,
    secondaryText = R.string.search_empty_secondary_text,
)
