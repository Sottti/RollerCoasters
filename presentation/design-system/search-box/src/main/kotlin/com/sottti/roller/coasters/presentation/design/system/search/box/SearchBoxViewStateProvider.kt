package com.sottti.roller.coasters.presentation.design.system.search.box

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME

internal class SearchBoxViewStateProvider : PreviewParameterProvider<SearchBoxViewState> {
    override val values: Sequence<SearchBoxViewState> = sequence {
        queryValues().forEach { query ->
            loadingValues().forEach { loading ->
                yield(
                    SearchBoxViewState(
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
