package com.sottti.roller.coasters.presentation.design.system.shapes.model

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.shapes.data.Corners

internal data class CardState(
    val content: @Composable ColumnScope.() -> Unit,
    val cornersKey: Int,
    val cornerKeyToValues: @Composable (Int) -> Corners,
    val modifier: Modifier,
    val onClick: () -> Unit,
)
