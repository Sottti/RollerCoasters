package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.presentation.search.model.SearchResultState
import com.sottti.roller.coasters.presentation.search.model.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SearchState>.updateQuery(query: String?): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(query = query)) } }

internal fun MutableStateFlow<SearchState>.updateClearIcon(query: String?): MutableStateFlow<SearchState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(showClearIcon = !query.isNullOrBlank())) } }

internal fun MutableStateFlow<SearchState>.loading(): MutableStateFlow<SearchState> =
    apply { update { it.copy(loading = true) } }

internal fun MutableStateFlow<SearchState>.notLoading(): MutableStateFlow<SearchState> =
    apply { update { it.copy(loading = false) } }

internal fun MutableStateFlow<SearchState>.updateResults(
    results: List<SearchResultState>,
): MutableStateFlow<SearchState> =
    apply { update { it.copy(results = results) } }
