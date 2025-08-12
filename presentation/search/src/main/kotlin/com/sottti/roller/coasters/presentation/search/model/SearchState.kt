package com.sottti.roller.coasters.presentation.search.model

import androidx.annotation.StringRes
import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class SearchState(
    val loading: Boolean,
    val searchBar: SearchBarState,
    val searchResults: SearchResults,
)

internal sealed interface SearchResults {
    data class Empty(
        @StringRes val primaryText: Int,
        @StringRes val secondaryText: Int,
    ) : SearchResults

    data class NotEmpty(
        val rollerCoasters: List<SearchResultState>,
    ) : SearchResults
}

internal data class SearchBarState(
    @StringRes val hint: Int,
    val query: String?,
    val showClearIcon: Boolean,
)

internal data class SearchResultState(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)