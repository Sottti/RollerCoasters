package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class ImageState(
    val contentDescription: String,
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl,
    val roundedCorners: Boolean,
)
