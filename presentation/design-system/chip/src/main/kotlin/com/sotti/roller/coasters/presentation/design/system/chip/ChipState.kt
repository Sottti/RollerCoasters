package com.sotti.roller.coasters.presentation.design.system.chip

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class ChipState(
    @StringRes val labelResId: Int,
    val expanded: Boolean?,
    val leadingIcon: IconState?,
    val onClick: () -> Unit = {},
    val selected: Boolean,
)
