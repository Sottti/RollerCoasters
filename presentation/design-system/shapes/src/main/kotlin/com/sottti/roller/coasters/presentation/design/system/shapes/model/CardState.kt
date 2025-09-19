package com.sottti.roller.coasters.presentation.design.system.shapes.model

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

internal data class CardState(
    val content: @Composable ColumnScope.() -> Unit,
    val corners: Corners,
    val elevation: Dp,
    val modifier: Modifier,
)
