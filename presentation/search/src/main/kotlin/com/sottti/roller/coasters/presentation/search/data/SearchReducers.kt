package com.sottti.roller.coasters.presentation.search.data

import com.sottti.roller.coasters.presentation.search.model.SearchResultViewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SearchViewState>.updateQuery(query: String): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(query = query)) } }

internal fun MutableStateFlow<SearchViewState>.updateClearIcon(query: String): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(showClearIcon = query.isNotBlank())) } }

internal fun MutableStateFlow<SearchViewState>.loading(): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(loading = true) } }

internal fun MutableStateFlow<SearchViewState>.notLoading(): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(loading = false) } }

internal fun MutableStateFlow<SearchViewState>.clearQuery(): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(searchBar = it.searchBar.copy(query = null, showClearIcon = false)) } }

internal fun MutableStateFlow<SearchViewState>.updateResults(
    results: List<SearchResultViewState>,
): MutableStateFlow<SearchViewState> =
    apply { update { it.copy(results = results) } }