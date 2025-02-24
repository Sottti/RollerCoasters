package com.sottti.roller.coasters.presentation.design.system.chip

import androidx.annotation.StringRes

internal data class ChipState(
    @StringRes val labelResId: Int,
    val expanded: Boolean,
    val onClick: () -> Unit = {},
    val selected: Boolean,
)