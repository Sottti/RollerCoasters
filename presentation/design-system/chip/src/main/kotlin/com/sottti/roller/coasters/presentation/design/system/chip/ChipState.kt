package com.sottti.roller.coasters.presentation.design.system.chip

import androidx.annotation.StringRes
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

internal data class ChipState(
    @StringRes val labelResId: Int,
    val expanded: Boolean?,
    val leadingIcon: IconState?,
    val onClick: () -> Unit = {},
    val selected: Boolean,
)