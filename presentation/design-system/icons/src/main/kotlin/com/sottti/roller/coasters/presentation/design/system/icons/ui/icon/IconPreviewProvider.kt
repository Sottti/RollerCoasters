package com.sottti.roller.coasters.presentation.design.system.icons.ui.icon

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons

internal class IconPreviewProvider : PreviewParameterProvider<IconPreviewState> {
    override val values: Sequence<IconPreviewState> = sequence {
        stateValues().forEach { state ->
            crossfadeValues().forEach { crossfade ->
                onClickValues().forEach { onClick ->
                    yield(
                        IconPreviewState(
                            crossfade = crossfade,
                            onClick = onClick,
                            iconState = state,
                        ),
                    )
                }
            }
        }
    }
}

private fun crossfadeValues() = sequenceOf(false, true)
private fun onClickValues() = sequenceOf({}, null)
private fun stateValues() = listOf(Icons.Explore.filled)
