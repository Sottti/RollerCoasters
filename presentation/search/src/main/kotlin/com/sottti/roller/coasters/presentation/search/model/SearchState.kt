package com.sottti.roller.coasters.presentation.search.model

import androidx.annotation.StringRes
import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class SearchState(
    val searchBar: SearchBarState,
    val loading: Boolean = false,
    val results: List<SearchResultState> = emptyList(),
)

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
