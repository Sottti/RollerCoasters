package com.sottti.roller.coasters.presentation.design.system.icons.ui.icon

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons

internal class IconPreviewProvider : PreviewParameterProvider<IconPreviewState> {
    override val values = buildList {
        stateValues().forEach { state ->
            crossfadeValues().forEach { crossfade ->
                onClickValues().forEach { onClick ->
                    add(
                        IconPreviewState(
                            crossfade = crossfade,
                            onClick = onClick,
                            iconState = state,
                        ),
                    )
                }
            }
        }
    }.asSequence()
}

private fun crossfadeValues() = sequenceOf(false, true)
private fun onClickValues() = sequenceOf({}, null)
private fun stateValues() = listOf(Icons.Explore.filled)
