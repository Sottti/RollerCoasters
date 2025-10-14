package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset

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
                    yOffset += placeable.height
                }
            }
        },
    )
}

@Composable
internal fun DownStairsLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }
            val width = placeables.sumOf { it.width }
            val height = placeables.sumOf { it.height }
            layout(width = width, height = height) {
                var xOffset = 0
                var yOffset = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = xOffset, y = yOffset)
                    xOffset += placeable.width
                    yOffset += placeable.height
                }
            }
        },
    )
}

@Composable
public fun DownStairsLayoutPreview() {
    DownStairsLayout {
        (1..10).forEach { a ->
            Text("Text $a", modifier = Modifier.background(Yellow))
        }
    }
}

@Composable
public fun CustomLayoutPreview() {
    CustomLayout {
        Text("Text", modifier = Modifier.background(Yellow))
        Button(
            modifier = Modifier.background(Blue),
            onClick = {},
        ) { Text("Button") }
    }
}


@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun SpecialBox() {
    Box(modifier = Modifier.size(500.dp)) {
        Button(
            modifier = Modifier
                .background(Yellow)
                .paddingModifier(
                    start = 10.dp,
                    top = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp,
                ),
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

public fun Modifier.centerInParent0(): Modifier =
    this then layout { measurable, constraints ->
        val maxWidthAllowedByParent = constraints.maxWidth
        val maxHeightAllowedByTheParent = constraints.maxHeight
        val placeable = measurable.measure(constraints)
        val start = maxWidthAllowedByParent / 2 - placeable.width / 2
        val top = maxHeightAllowedByTheParent / 2 - placeable.height / 2
        layout(width = placeable.width, height = placeable.height) {
            placeable.placeRelative(x = start, y = top)
        }
    }

public fun Modifier.paddingModifier(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp,
): Modifier {
    return this then layout { measurable, constraints ->
        val horizontal = (start + end).roundToPx()
        val vertical = (top + bottom).roundToPx()
        val placeable = measurable.measure(
            constraints.offset(horizontal = -horizontal, vertical = -vertical),
        )
        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        layout(width = width, height = height) {
            placeable.placeRelative(
                x = start.roundToPx(),
                y = top.roundToPx()
            )
        }
    }
}

public fun Modifier.centerInParent(): Modifier =
    this.then(
        layout { measurable, constraints ->
            val parentWidth = constraints.maxWidth
            val parentHeight = constraints.maxHeight

            val placeable = measurable.measure(constraints)

            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)


            val x = (parentWidth - placeable.width) / 2
            val y = (parentHeight - placeable.height) / 2

            layout(placeable.width, placeable.height) {
                placeable.placeRelative(x, y)
            }
        }
    )
