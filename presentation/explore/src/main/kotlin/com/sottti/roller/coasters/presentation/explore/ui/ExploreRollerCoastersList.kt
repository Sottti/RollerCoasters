package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster

@Composable
internal fun RollerCoastersList(
    listState: LazyListState,
    paddingValues: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (rollerCoasters.loadState.refresh) {
            is Loading -> FillMaxWidthProgressIndicator()
            is LoadState.Error -> Error()
            is NotLoading -> RollerCoasters(listState = listState, rollerCoasters = rollerCoasters)
        }
    }
}

@Composable
private fun RollerCoasters(
    listState: LazyListState,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium)
    ) {
        if (rollerCoasters.loadState.prepend is Loading) {
            item { FillMaxWidthProgressIndicator() }
        }

        items(rollerCoasters.itemCount) { index ->
            rollerCoasters[index]?.let { RollerCoaster(it) }
        }

        if (rollerCoasters.loadState.append is Loading) {
            item { FillMaxWidthProgressIndicator() }
        }
    }
}

@Composable
private fun RollerCoaster(
    rollerCoaster: ExploreRollerCoaster,
) {
    RollerCoasterCard(
        imageUrl = rollerCoaster.imageUrl,
        modifier = Modifier.fillMaxWidth(),
        parkName = rollerCoaster.parkName,
        rollerCoasterName = rollerCoaster.rollerCoasterName,
        stat = rollerCoaster.stat,
        statDetail = rollerCoaster.statDetail,
        onClick = {},
    )
}

@Composable
private fun Error() {
    Text.Vanilla("Error loading data")
}

@Composable
private fun FillMaxWidthProgressIndicator() {
    ProgressIndicator(
        modifier = Modifier
            .padding(vertical = dimensions.padding.medium)
            .fillMaxSize()
    )
}