package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchResult
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.search.model.toState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SearchState>.updateQuery(
    query: String?,
): MutableStateFlow<SearchState> =
    apply { update { state -> state.copy(searchBar = state.searchBar.copy(query = query)) } }

internal fun MutableStateFlow<SearchState>.updateClearIcon(
    query: String?,
): MutableStateFlow<SearchState> =
    apply {
        update { state ->
            state.copy(searchBar = state.searchBar.copy(showClearIcon = !query.isNullOrBlank()))
        }
    }

internal fun MutableStateFlow<SearchState>.loading(): MutableStateFlow<SearchState> =
    apply { update { state -> state.copy(searchBar = state.searchBar.copy(loading = true)) } }

internal fun MutableStateFlow<SearchState>.notLoading(): MutableStateFlow<SearchState> =
    apply { update { state -> state.copy(searchBar = state.searchBar.copy(loading = false)) } }

internal fun MutableStateFlow<SearchState>.onBlankQuery(): MutableStateFlow<SearchState> =
    apply { update { state -> state.copy(searchResult = searchResultsEmpty()) } }

internal fun MutableStateFlow<SearchState>.updateResults(
    rollerCoasters: List<RollerCoaster>,
): MutableStateFlow<SearchState> =
    apply { update { state -> state.copy(searchResult = rollerCoasters.toSearchResult()) } }

internal fun List<RollerCoaster>.toSearchResult(): SearchResult =
    when {
        isEmpty() -> searchResultsEmpty()
        else -> SearchResult.NotEmpty(rollerCoasters = this.toState())
    }

internal fun searchResultsEmpty(): SearchResult.Empty =
    SearchResult.Empty(
        primaryText = R.string.search_empty_primary_text,
        secondaryText = R.string.search_empty_secondary_text,
    )
