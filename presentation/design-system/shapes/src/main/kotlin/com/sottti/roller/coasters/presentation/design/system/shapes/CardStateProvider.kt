package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
            cardElevationValues().forEach { elevation ->
                yield(
                    CardState(
                        content = {},
                        corners = corners,
                        elevation = elevation,
                        modifier = Modifier
                            .padding(16.dp)
                            .width(200.dp)
                            .height(100.dp),
                    ),
                )
            }
        }
    }

    private fun cornersValues() = listOf(
        convexCorners(),
        concaveCorners(),
        cutCorners(),
        sharpCorners(),
        mixedCorners(),
    )

    private fun concaveCorners() = Corners(
        topStart = Corner.Concave(smallCornerSize),
        topEnd = Corner.Concave(mediumCornerSize),
        bottomEnd = Corner.Concave(largeCornerSize),
        bottomStart = Corner.Concave(extraLargeCornerSize),
    )

    private fun convexCorners() = Corners(
        topStart = Corner.Rounded(smallCornerSize),
        topEnd = Corner.Rounded(mediumCornerSize),
        bottomEnd = Corner.Rounded(largeCornerSize),
        bottomStart = Corner.Rounded(extraLargeCornerSize),
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
        topEnd = Corner.Rounded(mediumCornerSize),
        bottomEnd = Corner.Concave(largeCornerSize),
        bottomStart = Corner.Cut(extraLargeCornerSize),
    )

    private fun cardElevationValues() =
        listOf(0.dp, 1.dp, 3.dp, 6.dp, 8.dp, 12.dp)

    private val smallCornerSize = CornerSize(8.dp)
    private val mediumCornerSize = CornerSize(12.dp)
    private val largeCornerSize = CornerSize(16.dp)
    private val extraLargeCornerSize = CornerSize(28.dp)
}
