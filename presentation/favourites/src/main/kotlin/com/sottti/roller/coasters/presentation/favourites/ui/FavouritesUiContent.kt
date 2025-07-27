package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.empty.EmptyUi
import com.sottti.roller.coasters.presentation.error.ErrorButton
import com.sottti.roller.coasters.presentation.error.ErrorUi
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar


@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesContent(
    onNavigateToSettings: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    paddingValues: PaddingValues,
    lazyListState: LazyListState,
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                onNavigateToSettings = onNavigateToSettings,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPaddingValues ->
        val rememberedPaddingValues = remember(innerPaddingValues, paddingValues) {
            PaddingValues(
                start = innerPaddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = innerPaddingValues.calculateEndPadding(LayoutDirection.Ltr),
                top = innerPaddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            )
        }
        RollerCoasters(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            paddingValues = rememberedPaddingValues,
            rollerCoasters = rollerCoasters,
        )
    }
}


@Composable
private fun RollerCoasters(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onNavigateToRollerCoaster: (Int) -> Unit,
    paddingValues: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (rollerCoasters.loadState.refresh) {
            is Loading -> FillMaxWidthProgressIndicator()
            is LoadState.Error -> Error()
            is NotLoading -> {
                when (rollerCoasters.itemCount) {
                    0 -> Empty()
                    else -> RollerCoasters(
                        listState = listState,
                        nestedScrollConnection = nestedScrollConnection,
                        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                        rollerCoasters = rollerCoasters,
                    )
                }
            }
        }
    }
}

@Composable
private fun RollerCoasters(
    listState: LazyListState,
    nestedScrollConnection: NestedScrollConnection,
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensions.padding.medium),
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium),
    ) {
        if (rollerCoasters.loadState.prepend is Loading) {
            item { FillMaxWidthProgressIndicator() }
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

        if (rollerCoasters.loadState.append is Loading) {
            item { FillMaxWidthProgressIndicator() }
        }
    }
}

@Composable
private fun RollerCoaster(
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoaster: FavouritesRollerCoaster,
) {
    RollerCoasterCard.Small(
        modifier = Modifier.fillMaxSize(),
        onClick = { onNavigateToRollerCoaster(rollerCoaster.id) },
        imageUrl = rollerCoaster.imageUrl,
        parkName = rollerCoaster.parkName,
        rollerCoasterName = rollerCoaster.name,
    )
}

@Composable
private fun Empty() {
    EmptyUi()
}

@Composable
private fun Error() {
    ErrorUi(button = ErrorButton(onClick = {}))
}

@Composable
private fun FillMaxWidthProgressIndicator() {
    ProgressIndicator(
        modifier = Modifier
            .padding(vertical = dimensions.padding.medium)
            .fillMaxSize(),
    )
}