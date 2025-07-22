package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchResult

@Composable
internal fun SearchResults(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    paddingValues: PaddingValues,
    results: List<SearchResult>,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(dimensions.padding.medium),
            verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            items(results) { result ->
                RollerCoasterCard.Small(
                    imageUrl = result.imageUrl,
                    parkName = result.parkName,
                    rollerCoasterName = result.name,
                    onClick = { onNavigateToRollerCoaster(result.id) },
                )
            }
        }
    }
}