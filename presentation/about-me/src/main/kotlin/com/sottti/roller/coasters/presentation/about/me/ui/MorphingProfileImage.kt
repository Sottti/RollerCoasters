package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.StiffnessMedium
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.graphics.shapes.Morph
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.images.ui.Image
import com.sottti.roller.coasters.presentation.design.system.shapes.polygon.MorphPolygonShape
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes

@Composable
internal fun MorphingProfileImage(
    image: ImageState,
    modifier: Modifier = Modifier,
) {
    val startShape = shapes.roundedPolygon.hexagon
    val endShape = shapes.roundedPolygon.octagon
    val morph = remember(startShape, endShape) { Morph(start = startShape, end = endShape) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val progress by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "morphing_profile_image_progress",
        animationSpec = spring(
            dampingRatio = DampingRatioMediumBouncy,
            stiffness = StiffnessMedium,
        ),
    )
    val morphPolygonShape = MorphPolygonShape(morph = morph, percentage = progress)
    val haptic = LocalHapticFeedback.current

    Image(
        state = image,
        modifier = modifier
            .clip(morphPolygonShape)
            .border(
                width = dimensions.spacing.small,
                color = CardDefaults.cardColors().containerColor,
                shape = morphPolygonShape
            )
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.changes.any { it.pressed && !it.previousPressed }) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    }
                }
            }
            .clickable(interactionSource = interactionSource) {
                haptic.performHapticFeedback(HapticFeedbackType.KeyboardTap)
            }
    )
}
