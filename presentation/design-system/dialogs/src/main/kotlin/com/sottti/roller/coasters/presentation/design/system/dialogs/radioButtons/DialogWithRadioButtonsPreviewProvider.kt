package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

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
    FixturesR.fixture_confirm,
)

private fun dismissValues() = listOf(
    FixturesR.fixture_dismiss,
)

private fun optionsValues() = listOf(
    DialogRadioButtonOption(
        icon = Icons.BrightnessAuto.outlined,
        selected = false,
        text = FixturesR.fixture_option_a,
    ),
    DialogRadioButtonOption(
        icon = Icons.Sort.filled,
        selected = true,
        text = FixturesR.fixture_option_b,
    ),
)

private fun titleValues() = listOf(
    FixturesR.fixture_title,
    FixturesR.fixture_title_long,
)