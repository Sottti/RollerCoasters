package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.previews.data.PreviewsR

internal class DialogInformativePreviewProvider : PreviewParameterProvider<DialogInformativeState> {
    override val values = buildList {
        titleValues().forEach { title ->
            textValues().forEach { text ->
                dismissValues().forEach { dismiss ->
                    add(
                        DialogInformativeState(
                            dismiss = dismiss,
                            text = text,
                            title = title,
                        )
                    )
                }
            }
        }
    }.asSequence()
}

private fun dismissValues() = listOf(
    PreviewsR.dismiss,
)

private fun textValues() = listOf(
    PreviewsR.text,
    PreviewsR.text_long,
)

private fun titleValues() = listOf(
    PreviewsR.title,
    PreviewsR.title_long,
)