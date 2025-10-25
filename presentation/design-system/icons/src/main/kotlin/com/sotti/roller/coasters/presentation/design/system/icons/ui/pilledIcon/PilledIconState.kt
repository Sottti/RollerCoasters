package com.sotti.roller.coasters.presentation.design.system.icons.ui.pilledIcon

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class PilledIconState(
    @StringRes val text: Int,
    val iconState: IconState,
    val onClick: (() -> Unit),
)
