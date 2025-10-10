package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
internal fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val width = placeables.maxOf { it.width }
            val height = placeables.sumOf { it.height }
            layout(width = width, height = height) {
                var yOffset = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yOffset)
                    yOffset += placeable.height + 33
                }
            }
        },
    )
}
