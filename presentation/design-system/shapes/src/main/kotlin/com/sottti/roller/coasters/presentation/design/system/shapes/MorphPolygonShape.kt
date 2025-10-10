package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

@Composable
public fun morphPolygonShape(
    startShape: RoundedPolygon,
    endShape: RoundedPolygon,
    animationSpec: AnimationSpec<Float> =
        spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
): MorphPolygonShape {
    val morph = remember { Morph(start = startShape, end = endShape) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = animationSpec,
    )
    return MorphPolygonShape(morph = morph, percentage = animatedProgress.value)
}

public class MorphPolygonShape(
    private val morph: Morph,
    private val percentage: Float,
) : Shape {

    private val matrix = Matrix()

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        // Below assumes that you haven't changed the default radius of 1f,
        // nor the centerX and centerY of 0f
        // By default this stretches the path to the size of the container,
        // if you don't want stretching, use the same size.width for both x and y.
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)
        return Outline.Generic(path)
    }
}
