package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchBarViewState
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchResultViewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import com.sottti.roller.coasters.presentation.search.model.toViewState

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
    searchBar = searchBarState(query = COASTER_NAME.take(3), showClearIcon = true),
    loading = true
)

private val loadedState = searchPreviewState(
    searchBar = searchBarState(query = COASTER_NAME, showClearIcon = true),
    results = listOf(
        rollerCoaster().toViewState(),
        anotherRollerCoaster().toViewState(),
    ),
)

private fun searchPreviewState(
    loading: Boolean = false,
    results: List<SearchResultViewState> = emptyList(),
    searchBar: SearchBarViewState,
) = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    paddingValues = PaddingValues(),
    state = SearchViewState(
        searchBar = searchBar,
        loading = loading,
        results = results,
    ),
)

private fun searchBarState(
    query: String?,
    showClearIcon: Boolean,
) = SearchBarViewState(
    hint = R.string.search_hint,
    query = query,
    showClearIcon = showClearIcon,
)