package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

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
    FixturesR.dismiss,
)

private fun textValues() = listOf(
    FixturesR.text,
    FixturesR.text_long,
)

private fun titleValues() = listOf(
    FixturesR.title,
    FixturesR.title_long,
)