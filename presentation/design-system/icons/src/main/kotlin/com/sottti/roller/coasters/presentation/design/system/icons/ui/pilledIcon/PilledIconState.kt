package com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon

import androidx.annotation.StringRes
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class PilledIconState(
    @StringRes val text: Int,
    val iconState: IconState,
    val onClick: (() -> Unit),
)