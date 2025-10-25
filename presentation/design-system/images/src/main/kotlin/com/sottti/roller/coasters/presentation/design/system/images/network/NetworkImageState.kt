package com.sottti.roller.coasters.presentation.design.system.images.network

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class NetworkImageState(
    val contentDescription: String,
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl,
    val roundedCorners: Boolean,
)
