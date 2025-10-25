package com.sotti.roller.coasters.presentation.search.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class SearchState(
    val searchBar: SearchBarState,
    val searchResult: SearchResult,
)

@Immutable
internal sealed interface SearchResult {
    @Immutable
    data class Empty(
        @StringRes val primaryText: Int,
        @StringRes val secondaryText: Int,
    ) : SearchResult

    @Immutable
    data class NotEmpty(
        val rollerCoasters: List<SearchResultState>,
    ) : SearchResult
}

@Immutable
internal data class SearchBarState(
    @StringRes val hint: Int,
    val loading: Boolean,
    val query: String?,
)

@Immutable
internal data class SearchResultState(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)
