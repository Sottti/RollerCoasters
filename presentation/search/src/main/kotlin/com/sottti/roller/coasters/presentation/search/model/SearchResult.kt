package com.sottti.roller.coasters.presentation.search.model

import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class SearchResult(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)