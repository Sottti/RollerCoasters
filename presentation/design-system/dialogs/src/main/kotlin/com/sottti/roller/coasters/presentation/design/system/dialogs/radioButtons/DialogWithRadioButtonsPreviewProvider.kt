package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.previews.data.PreviewsR

internal class DialogWithRadioButtonsPreviewProvider :
    PreviewParameterProvider<DialogWithRadioButtonsState> {
    override val values = buildList {
        titleValues().forEach { title ->
            confirmValues().forEach { confirm ->
                dismissValues().forEach { dismiss ->
                    add(
                        DialogWithRadioButtonsState(
                            confirm = confirm,
                            dismiss = dismiss,
                            title = title,
                            options = optionsValues(),
                        )
                    )
                }
            }
        }
    }.asSequence()
}

private fun confirmValues() = listOf(
    PreviewsR.confirm,
)

private fun dismissValues() = listOf(
    PreviewsR.dismiss,
)

private fun optionsValues() = listOf(
    DialogRadioButtonOption(
        icon = Icons.BrightnessAuto.Outlined,
        selected = false,
        text = PreviewsR.option_a,
    ),
    DialogRadioButtonOption(
        icon = Icons.Sort.Filled,
        selected = true,
        text = PreviewsR.option_b,
    ),
)

private fun titleValues() = listOf(
    PreviewsR.title,
    PreviewsR.title_long,
)