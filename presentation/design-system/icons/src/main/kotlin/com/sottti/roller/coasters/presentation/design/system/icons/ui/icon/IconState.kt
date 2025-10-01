package com.sottti.roller.coasters.presentation.design.system.icons.ui.icon

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class IconState(
    val crossfade: Boolean,
    val iconState: IconState,
    val onClick: (() -> Unit)? = null,
)
