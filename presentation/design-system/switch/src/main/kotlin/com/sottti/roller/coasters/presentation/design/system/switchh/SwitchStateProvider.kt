package com.sottti.roller.coasters.presentation.design.system.switchh

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class SwitchStateProvider : PreviewParameterProvider<SwitchState> {
    override val values: Sequence<SwitchState> = sequence {
        enabledValues().forEach { enabled ->
            checkedValues().forEach { checked ->
                yield(
                    SwitchState(
                        checked = checked,
                        enabled = enabled,
                    ),
                )
            }
        }
    }
}

private fun checkedValues() = sequenceOf(true, false)
private fun enabledValues() = sequenceOf(true, false)