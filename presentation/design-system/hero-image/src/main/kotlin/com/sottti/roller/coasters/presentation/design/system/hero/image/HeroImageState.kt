package com.sottti.roller.coasters.presentation.design.system.hero.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState

@Immutable
internal data class HeroImageState(
    val modifier: Modifier,
    val image: ImageState,
)
