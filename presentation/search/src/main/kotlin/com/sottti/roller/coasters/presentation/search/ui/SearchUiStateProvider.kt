package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchBarViewState
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import com.sottti.roller.coasters.presentation.search.model.toViewState

internal class SearchUiStateProvider : PreviewParameterProvider<SearchPreviewState> {
    override val values: Sequence<SearchPreviewState> = sequenceOf(
        idleState,
        resultsState,
        loadingState,
    )
}

private val idleState = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    paddingValues = PaddingValues(),
    state = SearchViewState(
        searchBar = SearchBarViewState(
            hint = R.string.search_hint,
            query = null,
            showClearIcon = false,
        ),
        loading = false,
        results = emptyList(),
    ),
)

private val resultsState = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    paddingValues = PaddingValues(),
    state = SearchViewState(
        searchBar = SearchBarViewState(
            hint = R.string.search_hint,
            query = COASTER_NAME,
            showClearIcon = true,
        ),
        loading = false,
        results = listOf(
            rollerCoaster().toViewState(),
            anotherRollerCoaster().toViewState(),
        ),
    ),
)

private val loadingState = SearchPreviewState(
    onAction = {},
    onNavigateToRollerCoaster = {},
    onNavigateToSettings = {},
    paddingValues = PaddingValues(),
    state = SearchViewState(
        searchBar = SearchBarViewState(
            hint = R.string.search_hint,
            query = "Dr",
            showClearIcon = true,
        ),
        loading = true,
        results = emptyList(),
    ),
)