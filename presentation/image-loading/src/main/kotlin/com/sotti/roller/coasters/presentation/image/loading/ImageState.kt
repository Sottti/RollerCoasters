package com.sotti.roller.coasters.presentation.image.loading

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class ImageState(
    val contentDescription: String,
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl,
    val roundedCorners: Boolean,
)
