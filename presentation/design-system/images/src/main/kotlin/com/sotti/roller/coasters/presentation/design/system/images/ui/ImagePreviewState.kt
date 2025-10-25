package com.sotti.roller.coasters.presentation.design.system.images.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.sotti.roller.coasters.presentation.design.system.images.model.ImageState

@Immutable
internal data class ImagePreviewState(
    val modifier: Modifier,
    val roundedCorners: Boolean,
    val state: ImageState,
)
