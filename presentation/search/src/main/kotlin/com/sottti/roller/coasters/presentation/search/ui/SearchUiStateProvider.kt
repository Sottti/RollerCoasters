package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchResultState
import com.sottti.roller.coasters.presentation.search.model.SearchResults
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.search.model.toState

internal class SearchUiStateProvider : PreviewParameterProvider<SearchPreviewState> {
    override val values: Sequence<SearchPreviewState> = sequenceOf(
        initialState,
        loadedState,
        loadingState,
    )
}

private val initialState = searchPreviewState(
    searchBar = searchBarState(query = null, showClearIcon = false)
)

private val loadingState = searchPreviewState(
    searchBar = searchBarState(query = COASTER_NAME.take(3), showClearIcon = true), loading = true
)

private val loadedState = searchPreviewState(
    searchBar = searchBarState(query = COASTER_NAME, showClearIcon = true),
    results = listOf(
        rollerCoaster().toState(),
        anotherRollerCoaster().toState(),
    ),
)

private fun searchPreviewState(
    loading: Boolean = false,
    results: List<SearchResultState> = emptyList(),
    searchBar: SearchBarState,
) = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    padding = PaddingValues(),
    state = SearchState(
        searchBar = searchBar,
        loading = loading,
        searchResults = SearchResults.NotEmpty(rollerCoasters = results),
    ),
)

private fun searchBarState(
    query: String?,
    showClearIcon: Boolean,
) = SearchBarState(
    hint = R.string.search_hint,
    query = query,
    showClearIcon = showClearIcon,
)