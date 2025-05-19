package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
internal fun topStartRoundedShape(): CornerBasedShape =
    MaterialTheme.shapes.large.copy(
        bottomEnd = ZeroCornerSize,
        bottomStart = ZeroCornerSize,
        topEnd = ZeroCornerSize,
    )

@Composable
internal fun topEndRoundedShape(): CornerBasedShape =
    MaterialTheme.shapes.large.copy(
        bottomEnd = ZeroCornerSize,
        bottomStart = ZeroCornerSize,
        topStart = ZeroCornerSize,
    )

@Composable
internal fun bottomEndRoundedShape(): CornerBasedShape =
    MaterialTheme.shapes.large.copy(
        bottomStart = ZeroCornerSize,
        topEnd = ZeroCornerSize,
        topStart = ZeroCornerSize,
    )

@Composable
internal fun bottomStartRoundedShape(): CornerBasedShape =
    MaterialTheme.shapes.large.copy(
        bottomEnd = ZeroCornerSize,
        topEnd = ZeroCornerSize,
        topStart = ZeroCornerSize,
    )