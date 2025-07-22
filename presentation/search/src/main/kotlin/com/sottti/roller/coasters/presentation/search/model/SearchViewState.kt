package com.sottti.roller.coasters.presentation.search.model

import androidx.annotation.StringRes
import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class SearchViewState(
    val searchBar: SearchBarViewState,
    val loading: Boolean = false,
    val results: List<SearchResultViewState> = emptyList(),
)

internal data class SearchBarViewState(
    @StringRes val hint: Int,
    val query: String?,
    val showClearIcon: Boolean,
)

internal data class SearchResultViewState(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)