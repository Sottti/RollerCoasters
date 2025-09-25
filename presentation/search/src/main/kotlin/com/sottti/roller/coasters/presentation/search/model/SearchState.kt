package com.sottti.roller.coasters.presentation.search.model

import androidx.annotation.StringRes
import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class SearchState(
    val searchBar: SearchBarState,
    val searchResult: SearchResult,
)

internal sealed interface SearchResult {
    data class Empty(
        @StringRes val primaryText: Int,
        @StringRes val secondaryText: Int,
    ) : SearchResult

    data class NotEmpty(
        val rollerCoasters: List<SearchResultState>,
    ) : SearchResult
}

internal data class SearchBarState(
    @StringRes val hint: Int,
    val loading: Boolean,
    val query: String?,
    val showClearIcon: Boolean,
)

internal data class SearchResultState(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)
