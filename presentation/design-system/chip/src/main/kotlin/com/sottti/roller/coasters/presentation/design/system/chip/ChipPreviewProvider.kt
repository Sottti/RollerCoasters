package com.sottti.roller.coasters.presentation.design.system.chip

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.sotti.roller.coasters.presentation.design.system.icons.data.Icons

internal class ChipPreviewProvider : PreviewParameterProvider<ChipState> {
    override val values = buildList {
        leadingIconValues().forEach { leadingIcon ->
            selectedValues().forEach { enabled ->
                expandedValues().forEach { expanded ->
                    labelResIdValues().forEach { checked ->
                        add(
                            ChipState(
                                expanded = expanded,
                                labelResId = checked,
                                leadingIcon = leadingIcon,
                                selected = enabled,
                            )
                        )
                    }
                }
            }
        }
    }.asSequence()
}

private fun expandedValues() = sequenceOf(false, true, null)
private fun labelResIdValues() = sequenceOf(R.string.chip_label, R.string.chip_label_long)
private fun leadingIconValues() = sequenceOf(null, Icons.CheckSmall.filled)
private fun selectedValues() = sequenceOf(false, true)