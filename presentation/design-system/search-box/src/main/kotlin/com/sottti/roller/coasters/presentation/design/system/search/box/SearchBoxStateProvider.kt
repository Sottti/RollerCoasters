package com.sottti.roller.coasters.presentation.design.system.search.box

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME

internal class SearchBoxStateProvider : PreviewParameterProvider<SearchBoxState> {
    override val values: Sequence<SearchBoxState> = sequence {
        queryValues().forEach { query ->
            loadingValues().forEach { loading ->
                yield(
                    SearchBoxState(
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
