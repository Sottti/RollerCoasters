package com.sottti.roller.coasters.presentation.design.system.images.local

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.images.local.model.ImageState

@Immutable
internal data class ImagePreviewState(
    val modifier: Modifier,
    val roundedCorners: Boolean,
    val state: ImageState,
)
