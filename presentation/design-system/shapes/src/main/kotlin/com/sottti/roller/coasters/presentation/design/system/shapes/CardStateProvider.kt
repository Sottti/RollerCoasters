package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.shapes.data.Corners
import com.sottti.roller.coasters.presentation.design.system.shapes.model.CardState
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner

internal class CardStateProvider : PreviewParameterProvider<CardState> {
    override val values: Sequence<CardState> = sequence {
        cornersKeys.forEach { cornersKey ->
            yield(
                CardState(
                    content = {},
                    cornersKey = cornersKey,
                    cornerKeyToValues = { key -> cornersValues().getValue(key) },
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp),
                    onClick = {},
                ),
            )
        }
    }
}

private val cornersKeys = listOf(0, 1, 2)

@Composable
private fun cornersValues() = mapOf(
    cornersKeys[0] to convexCorners(),
    cornersKeys[1] to concaveCorners(),
    cornersKeys[2] to sharpCorners(),
)

@Composable
private fun concaveCorners() = Corners(
    bottomEnd = Corner.Concave(cornerSizeBottomEnd()),
    bottomStart = Corner.Concave(cornerSizeBottomStart()),
    topEnd = Corner.Concave(cornerSizeTopEnd()),
    topStart = Corner.Concave(cornerSizeTopStart()),
)

@Composable
private fun convexCorners() = Corners(
    bottomEnd = Corner.Convex(cornerSizeBottomEnd()),
    bottomStart = Corner.Convex(cornerSizeBottomStart()),
    topEnd = Corner.Convex(cornerSizeTopEnd()),
    topStart = Corner.Convex(cornerSizeTopStart()),
)

private fun sharpCorners() = Corners(
    bottomEnd = Corner.Sharp,
    bottomStart = Corner.Sharp,
    topEnd = Corner.Sharp,
    topStart = Corner.Sharp,
)

@Composable
private fun cornerSizeBottomEnd() = MaterialTheme.shapes.large.bottomEnd

@Composable
private fun cornerSizeBottomStart() = MaterialTheme.shapes.large.bottomStart

@Composable
private fun cornerSizeTopEnd() = MaterialTheme.shapes.large.topEnd

@Composable
private fun cornerSizeTopStart() = MaterialTheme.shapes.large.topStart
