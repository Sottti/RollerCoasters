package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class DialogInformativeStateProvider : PreviewParameterProvider<DialogInformativeState> {
    override val values: Sequence<DialogInformativeState> = sequence {
        titleValues().forEach { title ->
            textValues().forEach { text ->
                dismissValues().forEach { dismiss ->
                    yield(
                        DialogInformativeState(
                            dismiss = dismiss,
                            text = text,
                            title = title,
                        ),
                    )
                }
            }
        }
    }
}

private fun dismissValues() = listOf(
    FixturesR.fixture_dismiss,
)

private fun textValues() = listOf(
    FixturesR.fixture_text_explainer,
    FixturesR.fixture_text_explainer_long,
)

private fun titleValues() = listOf(
    FixturesR.fixture_title,
    FixturesR.fixture_title_long,
)