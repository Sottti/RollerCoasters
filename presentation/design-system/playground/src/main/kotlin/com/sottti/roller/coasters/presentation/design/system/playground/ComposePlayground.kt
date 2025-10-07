package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
private fun Playground1() {
    val backgroundColor by animateColorBetween(Color.Magenta, Color.Green)
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(4.dp)
    ) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
private fun Playground2() {
    val backgroundColor by animateColorBetween(Color.Magenta, Color.Green)
    Box(
        modifier = Modifier
            .drawBehind { drawRect(color = backgroundColor) }
            .padding(4.dp)) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
private fun animateColorBetween(start: Color, end: Color): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse,
        )
    )
}


@Composable
@RollerCoastersPreviewNoLocale
private fun MyPreview() {
    RollerCoastersPreviewTheme {
        Playground1()
    }
}
