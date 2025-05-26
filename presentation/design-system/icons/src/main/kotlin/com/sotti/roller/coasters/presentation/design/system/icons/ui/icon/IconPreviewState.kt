package com.sotti.roller.coasters.presentation.design.system.icons.ui.icon

import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class IconPreviewState(
    val crossfade: Boolean,
    val iconState: IconState,
    val onClick: (() -> Unit)? = null,
)