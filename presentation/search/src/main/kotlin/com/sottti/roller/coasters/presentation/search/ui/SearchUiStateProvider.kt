package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.data.toSearchResult
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchState

internal class SearchUiStateProvider : PreviewParameterProvider<SearchPreviewState> {
    override val values: Sequence<SearchPreviewState> = sequenceOf(
        initialState,
        loadingState,
        loadedState,
    )
}

private val initialState = searchPreviewState(
    searchBar = searchBarState(
        loading = false,
        query = null,
    ),
    searchResults = emptyList()
)

private val loadingState = searchPreviewState(
    searchBar = searchBarState(
        loading = true,
        query = COASTER_NAME.take(n = 3),
    ),
    searchResults = emptyList()
)

private val loadedState = searchPreviewState(
    searchBar = searchBarState(
        query = COASTER_NAME,
        loading = false,
    ),
    searchResults = listOf(
        rollerCoaster(),
        anotherRollerCoaster(),
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
private fun searchPreviewState(
    searchResults: List<RollerCoaster>,
    searchBar: SearchBarState,
) = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    onListCreated = { _, _ -> },
    padding = PaddingValues(),
    state = SearchState(
        searchBar = searchBar,
        searchResult = searchResults.toSearchResult(),
    ),
)

private fun searchBarState(
    loading: Boolean,
    query: String?,
) = SearchBarState(
    hint = R.string.search_hint,
    loading = loading,
    query = query,
)
