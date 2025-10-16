package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import kotlin.math.min

@Composable
@RollerCoastersPreviewNoLocale
private fun DefaultMinSize0() {
    BoxWithConstraints { }
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.DarkGray)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .background(Color.LightGray)
                .circledRainbowBorder(2.dp)
        )
    }
}

private fun Modifier.circledRainbowBorder(
    strokeWidth: Dp,
): Modifier = drawWithContent {
    val strokeWidthInPx = strokeWidth.toPx()
    drawCircle(
        color = Color.Yellow,
        radius = min(size.width / 2, size.height / 2) - strokeWidthInPx
    )
    drawContent()
    drawCircle(
        brush = Brush.linearGradient(listOf(Color.Magenta, Color.Blue)),
        radius = min(size.width / 2, size.height / 2) - strokeWidthInPx * 0.5f,
        style = Stroke(width = strokeWidthInPx),
    )
}
