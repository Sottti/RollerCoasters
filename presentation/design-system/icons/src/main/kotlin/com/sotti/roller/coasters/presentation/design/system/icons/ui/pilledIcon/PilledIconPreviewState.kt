package com.sotti.roller.coasters.presentation.design.system.icons.ui.pilledIcon

import androidx.annotation.StringRes
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class PilledIconPreviewState(
    @StringRes val text: Int,
    val iconState: IconState,
    val onClick: (() -> Unit),
)