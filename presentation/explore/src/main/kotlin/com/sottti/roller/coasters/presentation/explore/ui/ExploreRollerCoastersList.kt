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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.loading.LoadingFullScreen
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import kotlinx.coroutines.flow.Flow

@Composable
internal fun RollerCoastersList(
    listState: LazyListState,
    paddingValues: PaddingValues,
    state: Flow<PagingData<ExploreRollerCoaster>>,
) {
    val rollerCoasters: LazyPagingItems<ExploreRollerCoaster> = state.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        when (rollerCoasters.loadState.refresh) {
            is LoadState.Loading -> LoadingFullScreen()
            is LoadState.Error -> Error()
            else -> RollerCoasters(listState = listState, rollerCoasters = rollerCoasters)
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
        items(rollerCoasters.itemCount) { index ->
            rollerCoasters[index]?.let { RollerCoaster(it) }
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
    )
}

@Composable
private fun Error() {
    Text.Vanilla("Error loading data")
}