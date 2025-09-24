package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchResult
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.search.model.toState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SearchState>.updateQuery(query: String?): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(query = query)) } }

internal fun MutableStateFlow<SearchState>.updateClearIcon(query: String?): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(showClearIcon = !query.isNullOrBlank())) } }

internal fun MutableStateFlow<SearchState>.loading(): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(loading = true)) } }

internal fun MutableStateFlow<SearchState>.notLoading(): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(loading = false)) } }

internal fun MutableStateFlow<SearchState>.updateResults(
    results: List<RollerCoaster>,
): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchResult = results.toSearchResult()) } }

internal fun List<RollerCoaster>.toSearchResult(): SearchResult =
    when {
        isEmpty() -> searchResultEmpty()
        else -> SearchResult.NotEmpty(rollerCoasters = this.toState())
    }

internal fun searchResultEmpty(): SearchResult.Empty = SearchResult.Empty(
    primaryText = R.string.search_empty_primary_text,
    secondaryText = R.string.search_empty_secondary_text,
)
