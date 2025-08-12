package com.sottti.roller.coasters.presentation.top.bars.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.R

internal class MainTopBarUiStateProvider : PreviewParameterProvider<MainTopBarState> {
    override val values: Sequence<MainTopBarState> = sequence {
        showTitleValues().forEach { showTitle ->
            titleResIdValues().forEach { titleResId ->
                yield(
                    MainTopBarState(
                        showTitle = showTitle,
                        titleResId = titleResId,
                        onNavigateToSettings = {},
                        scrollBehavior = null
                    )
                )
            }
        }
    }
}

private fun showTitleValues() = listOf(false, true)
private fun titleResIdValues() = listOf(
    null,
    R.string.fixture_title,
    R.string.fixture_title_long,
)