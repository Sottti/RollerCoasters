package com.sottti.roller.coasters.presentation.design.system.switchh

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class SwitchPreviewProvider : PreviewParameterProvider<SwitchState> {
    override val values = buildList {
        enabledValues().forEach { enabled ->
            checkedValues().forEach { checked ->
                add(
                    SwitchState(
                        checked = checked,
                        enabled = enabled,
                    ),
                )
            }
        }
    }.asSequence()
}

private fun checkedValues() = sequenceOf(true, false)
private fun enabledValues() = sequenceOf(true, false)