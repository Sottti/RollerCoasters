package com.sotti.roller.coasters.presentation.search.ui

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
import com.sotti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sotti.roller.coasters.presentation.design.system.empty.EmptyUi
import com.sotti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sotti.roller.coasters.presentation.search.model.SearchAction
import com.sotti.roller.coasters.presentation.search.model.SearchResult
import com.sotti.roller.coasters.presentation.search.model.SearchState
import com.sotti.roller.coasters.presentation.utils.override

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUiContent(
    lazyListState: LazyListState,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    outerPadding: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SearchState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                lazyListState = lazyListState,
                onAction = onAction,
                onNavigateToSettings = onNavigateToSettings,
                state = state,
            )
        },
    ) { innerPadding ->
        SearchResults(
            listState = lazyListState,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = innerPadding.override(bottom = outerPadding.calculateBottomPadding()),
            scrollBehavior = scrollBehavior,
            state = state.searchResult,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
private fun SearchResults(
    listState: LazyListState,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SearchResult,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = { fadeIn() togetherWith fadeOut() }
    ) { targetState ->
        when (targetState) {
            is SearchResult.Empty -> EmptyUi(
                primaryText = targetState.primaryText,
                secondaryText = targetState.secondaryText,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )

            is SearchResult.NotEmpty -> LazyColumn(
                state = listState,
                contentPadding = padding + PaddingValues(dimensions.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(connection = scrollBehavior.nestedScrollConnection)

            ) {
                items(
                    items = targetState.rollerCoasters,
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
