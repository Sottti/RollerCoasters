package com.sottti.roller.coasters.presentation.design.system.card.model

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

internal data class CardState(
    val content: @Composable ColumnScope.() -> Unit,
    val corners: Corners,
    val modifier: Modifier,
    val onClick: () -> Unit,
)