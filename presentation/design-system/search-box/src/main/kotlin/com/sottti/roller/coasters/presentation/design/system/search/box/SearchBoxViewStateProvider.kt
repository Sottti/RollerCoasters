package com.sottti.roller.coasters.presentation.design.system.search.box

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.domain.fixtures.COASTER_NAME

internal class SearchBoxViewStateProvider : PreviewParameterProvider<SearchBoxViewState> {
    override val values = buildList {
        queryValues().forEach { query ->
            loadingValues().forEach { loading ->
                add(
                    SearchBoxViewState(
                        hint = R.string.hint,
                        query = query,
                        loading = loading,
                        onQueryChange = {},
                    )
                )
            }
        }
    }.asSequence()
}

private fun loadingValues() = listOf(false, true)
private fun queryValues() = listOf(null, COASTER_NAME)