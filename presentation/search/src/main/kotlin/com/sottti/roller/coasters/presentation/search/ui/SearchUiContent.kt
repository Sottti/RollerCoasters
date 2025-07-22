package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchAction.ClearQuery
import com.sottti.roller.coasters.presentation.search.model.SearchResultViewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUiContent(
    state: SearchViewState,
    onAction: (SearchAction) -> Unit,
    rememberedPaddingValues: PaddingValues,
    listState: LazyListState,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateToRollerCoaster: (Int) -> Unit,
    paddingValues: PaddingValues,
) {
    Column(modifier = Modifier.padding(rememberedPaddingValues)) {
        TextField(
            value = state.searchBar.query.orEmpty(),
            onValueChange = { onAction(SearchAction.QueryChanged(it)) },
            placeholder = { Text.Vanilla(state.searchBar.hint) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.padding.medium),
            trailingIcon = {
                if (state.searchBar.showClearIcon) {
                    Icon(
                        iconState = Icons.Cancel.outlined,
                        onClick = { onAction(ClearQuery) })
                }
            }
        )

        SearchResults(
            listState = listState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            results = state.results,
        )
    }
}

@Composable
internal fun SearchResults(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    results: List<SearchResultViewState>,
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