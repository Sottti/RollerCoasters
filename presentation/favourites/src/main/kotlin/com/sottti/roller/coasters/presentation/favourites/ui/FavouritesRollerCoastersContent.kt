package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@Composable
internal fun FavouritesRollerCoastersContent(
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

        items(rollerCoasters.itemCount) { index ->
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
            .fillMaxSize()
    )
}