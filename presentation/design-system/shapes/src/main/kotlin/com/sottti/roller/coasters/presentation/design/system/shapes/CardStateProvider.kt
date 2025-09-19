package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.shapes.model.CardState
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corners

internal class CardStateProvider : PreviewParameterProvider<CardState> {
    override val values: Sequence<CardState> = sequence {
        cornersValues().forEach { corners ->
            yield(
                CardState(
                    content = {},
                    corners = corners,
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp),
                    onClick = {},
                ),
            )
        }
    }
}

private fun cornersValues() = listOf(
    concaveCorners(),
    convexCorners(),
    sharpCorners(),
    cutCorners(),
    mixedCorners(),
)

private fun concaveCorners() = Corners(
    topStart = Corner.Concave(smallCornerSize),
    topEnd = Corner.Concave(mediumCornerSize),
    bottomEnd = Corner.Concave(largeCornerSize),
    bottomStart = Corner.Concave(extraLargeCornerSize),
)

private fun convexCorners() = Corners(
    topStart = Corner.Convex(smallCornerSize),
    topEnd = Corner.Convex(mediumCornerSize),
    bottomEnd = Corner.Convex(largeCornerSize),
    bottomStart = Corner.Convex(extraLargeCornerSize),
)

private fun sharpCorners() = Corners(
    bottomStart = Corner.Sharp,
    topStart = Corner.Sharp,
    bottomEnd = Corner.Sharp,
    topEnd = Corner.Sharp,
)

private fun cutCorners() = Corners(
    topStart = Corner.Cut(smallCornerSize),
    topEnd = Corner.Cut(mediumCornerSize),
    bottomEnd = Corner.Cut(largeCornerSize),
    bottomStart = Corner.Cut(extraLargeCornerSize),
)

private fun mixedCorners() = Corners(
    topStart = Corner.Sharp,
    topEnd = Corner.Convex(mediumCornerSize),
    bottomEnd = Corner.Concave(largeCornerSize),
    bottomStart = Corner.Cut(extraLargeCornerSize),
)

private val smallCornerSize = CornerSize(4.dp)
private val mediumCornerSize = CornerSize(8.dp)
private val largeCornerSize = CornerSize(16.dp)
private val extraLargeCornerSize = CornerSize(32.dp)
