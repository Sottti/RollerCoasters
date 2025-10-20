package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale
import kotlin.time.Duration.Companion.seconds

@Composable
private fun BackgroundArg() {
    val backgroundColor by animateColorBetween(Color.Magenta, Color.Green)
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
internal fun animateColorBetween(start: Color, end: Color): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2.seconds.inWholeMilliseconds.toInt()),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
private fun animateAlphaBetween(start: Float, end: Float): State<Float> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateFloat(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2.seconds.inWholeMilliseconds.toInt()),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
@RollerCoastersPreviewNoLocale
private fun MyPreview() {
    RollerCoastersTheme {
        BackgroundArg()
    }
}
