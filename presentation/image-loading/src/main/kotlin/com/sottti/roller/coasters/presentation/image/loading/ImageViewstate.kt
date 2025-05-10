package com.sottti.roller.coasters.presentation.image.loading

import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class ImageViewState(
    val contentDescription: String,
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl,
    val roundedCorners: Boolean,
)