package com.sotti.roller.coasters.presentation.design.system.card.grid.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
internal data class MonoCardGridState(
    @StringRes val textResId: Int,
    val modifier: Modifier,
    val onClick: () -> Unit,
)
