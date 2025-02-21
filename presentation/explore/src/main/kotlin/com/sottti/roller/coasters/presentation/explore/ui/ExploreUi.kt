package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel
import com.sottti.roller.coasters.presentation.explore.model.RollerCoasterUiModel

@Composable
public fun ExploreUi(
    nestedScrollConnection: NestedScrollConnection,
) {
    ExploreUiInternal(nestedScrollConnection = nestedScrollConnection)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreUiInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters: LazyPagingItems<RollerCoasterUiModel> =
        state.rollerCoastersFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        when (rollerCoasters.loadState.refresh) {
            is LoadState.Loading -> Loading()
            is LoadState.Error -> Error()
            else -> RollerCoasters(nestedScrollConnection, rollerCoasters)
        }
    }
}

@Composable
private fun RollerCoasters(
    nestedScrollConnection: NestedScrollConnection,
    rollerCoasters: LazyPagingItems<RollerCoasterUiModel>,
) {
    LazyColumn(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
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
    rollerCoaster: RollerCoasterUiModel,
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
private fun Loading() {
    Text("Loading...")
}

@Composable
private fun Error() {
    Text("Error loading data")
}