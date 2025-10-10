package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.toPath
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
@RollerCoastersPreviewNoLocale
private fun Morphing() {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
    val morphProgress = infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(500), repeatMode = Reverse),
        label = "morph",
    )
    Box(
        modifier = Modifier
            .drawWithCache {
                val morphPath =
                    Morph(start = triangle(), end = square())
                        .toPath(progress = morphProgress.value)
                        .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color.Black)
                }
            }
            .fillMaxSize()
    )
}
