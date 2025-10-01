package com.sottti.roller.coasters.presentation.design.system.card.grid.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
internal data class MonoCardGridState(
    val item: Int,
    val modifier: Modifier,
    val onClick: () -> Unit,
)
