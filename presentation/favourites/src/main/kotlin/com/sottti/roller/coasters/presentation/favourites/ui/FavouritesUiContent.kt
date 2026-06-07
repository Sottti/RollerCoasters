package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.empty.EmptyUi
import com.sottti.roller.coasters.presentation.design.system.error.ErrorButton
import com.sottti.roller.coasters.presentation.design.system.error.ErrorUi
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.favourites.R
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.top.bars.ui.MainTopBar
import com.sottti.roller.coasters.presentation.utils.override
import com.sottti.roller.coasters.presentation.utils.plus


@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesContent(
    lazyListState: LazyListState,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    outerPadding: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                onNavigateToSettings = onNavigateToSettings,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        RollerCoasters(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = innerPadding.override(bottom = outerPadding.calculateBottomPadding()),
            rollerCoasters = rollerCoasters,
        )
    }
}


@Composable
private fun RollerCoasters(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (rollerCoasters.loadState.refresh) {
            is Loading -> ProgressIndicatorFillMaxSize(padding)
            is LoadState.Error -> ErrorUi(
                modifier = Modifier.padding(padding),
                button = ErrorButton(onClick = {})
            )

            is NotLoading -> when (rollerCoasters.itemCount) {
                0 -> EmptyUi(modifier = Modifier.padding(padding))
                else -> LoadedRollerCoasters(
                    listState = listState,
                    nestedScrollConnection = nestedScrollConnection,
                    onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                    padding = padding,
                    rollerCoasters = rollerCoasters,
                )
            }
        }
    }
}

@Composable
private fun LoadedRollerCoasters(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    LazyColumn(
        contentPadding = padding + PaddingValues(dimensions.spacing.medium),
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
    ) {
        when (rollerCoasters.loadState.prepend) {
            is Error -> item(key = "prepend error") {
                PaginationErrorItem(onRetry = { rollerCoasters.retry() })
            }

            is Loading -> item(key = "prepend loading") { ProgressIndicatorFillMaxWidth() }
            else -> Unit
        }

        items(
            rollerCoasters.itemCount,
            key = { index -> rollerCoasters[index]?.id ?: index }
        ) { index ->
            rollerCoasters[index]?.let { rollerCoaster ->
                RollerCoaster(
                    onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                    rollerCoaster = rollerCoaster,
                )
            }
        }

        when (rollerCoasters.loadState.append) {
            is Error -> item(key = "append error") {
                PaginationErrorItem(onRetry = { rollerCoasters.retry() })
            }

            is Loading -> item(key = "append loading") { ProgressIndicatorFillMaxWidth() }
            else -> Unit
        }
    }
}

@Composable
private fun RollerCoaster(
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoaster: FavouritesRollerCoaster,
) {
    RollerCoasterCard.Small(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onNavigateToRollerCoaster(rollerCoaster.id) },
        imageUrl = rollerCoaster.imageUrl,
        parkName = rollerCoaster.parkName,
        rollerCoasterName = rollerCoaster.name,
    )
}

@Composable
private fun ProgressIndicatorFillMaxSize(
    padding: PaddingValues,
) {
    ProgressIndicator(modifier = Modifier
        .padding(padding)
        .fillMaxSize())
}

@Composable
private fun ProgressIndicatorFillMaxWidth() {
    ProgressIndicator(
        modifier = Modifier
            .padding(PaddingValues(vertical = dimensions.spacing.medium))
            .fillMaxWidth(),
    )
}

@Composable
private fun PaginationErrorItem(
    onRetry: () -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small),
        ) {
            Text.Body.Medium(
                textResId = R.string.pagination_error,
                textAlign = TextAlign.Center,
            )
            Button(onClick = onRetry) {
                Text.Vanilla(R.string.pagination_error_retry)
            }
        }
    }
}
