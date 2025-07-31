package com.sottti.roller.coasters.presentation.design.system.images.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.images.data.Images

internal class ImageStateProvider : PreviewParameterProvider<ImagePreviewState> {
    override val values = sequence {
        roundedCornerValues().forEach { roundedCorners ->
            stateValues().forEach { state ->
                modifierValues().forEach { modifier ->
                    yield(
                        ImagePreviewState(
                            state = state,
                            roundedCorners = roundedCorners,
                            modifier = modifier,
                        )
                    )
                }
            }
        }
    }
}

private fun modifierValues() = listOf(Modifier)
private fun roundedCornerValues() = listOf(false, true)
private fun stateValues() = listOf(Images.Map.state)