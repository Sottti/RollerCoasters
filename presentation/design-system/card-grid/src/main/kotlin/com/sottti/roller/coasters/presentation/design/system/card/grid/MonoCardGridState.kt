package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.ui.Modifier

internal data class MonoCardGridState(
    val item: Int,
    val modifier: Modifier,
    val onClick: () -> Unit,
)
