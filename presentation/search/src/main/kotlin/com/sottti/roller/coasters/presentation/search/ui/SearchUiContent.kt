package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.empty.EmptyUi
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchResultViewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import com.sottti.roller.coasters.presentation.utils.override
import com.sottti.roller.coasters.presentation.utils.plus

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUiContent(
    listState: LazyListState,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    outerPadding: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SearchViewState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                lazyListState = listState,
                onAction = onAction,
                onNavigateToSettings = onNavigateToSettings,
                state = state,
            )
        },
    ) { innerPadding ->
        SearchResults(
            listState = listState,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = innerPadding.override(bottom = outerPadding.calculateBottomPadding()),
            scrollBehavior = scrollBehavior,
            state = state.results,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
private fun SearchResults(
    listState: LazyListState,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
    state: List<SearchResultViewState>,
) {
    AnimatedContent(
        targetState = state.isEmpty(),
        transitionSpec = { fadeIn() togetherWith fadeOut() }
    ) { isEmpty ->
        when {
            isEmpty -> EmptyUi(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )

            else -> LazyColumn(
                state = listState,
                contentPadding = padding + PaddingValues(dimensions.padding.medium),
                verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium),
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(connection = scrollBehavior.nestedScrollConnection)

            ) {
                items(
                    items = state,
                    key = { result -> result.id }
                ) { result ->
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
}
