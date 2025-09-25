package com.sottti.roller.coasters.presentation.design.system.search.bar

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME

internal class SearchBarStateProvider : PreviewParameterProvider<SearchBarState> {
    override val values: Sequence<SearchBarState> = sequence {
        queryValues().forEach { query ->
            loadingValues().forEach { loading ->
                yield(
                    SearchBarState(
                        hint = R.string.hint,
                        query = query,
                        loading = loading,
                        onQueryChange = {},
                    )
                )
            }
        }
    }
}

private fun loadingValues() = listOf(false, true)
private fun queryValues() = listOf(null, COASTER_NAME)
