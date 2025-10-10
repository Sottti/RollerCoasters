package com.sottti.roller.coasters.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
public fun Modifier.onClickPressAndReleaseHaptics(
    interactionSource: MutableInteractionSource,
): Modifier = onClickPressHaptics(interactionSource)
    .onClickReleaseHaptics()

@Composable
public fun Modifier.onClickPressHaptics(
    interactionSource: MutableInteractionSource,
): Modifier {
    val haptic = LocalHapticFeedback.current
    return clickable(interactionSource = interactionSource) {
        haptic.performHapticFeedback(HapticFeedbackType.KeyboardTap)
    }
}

@Composable
public fun Modifier.onClickReleaseHaptics(): Modifier {
    val haptic = LocalHapticFeedback.current
    return pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                if (event.changes.any { it.pressed && !it.previousPressed }) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            }
        }
    }
}
