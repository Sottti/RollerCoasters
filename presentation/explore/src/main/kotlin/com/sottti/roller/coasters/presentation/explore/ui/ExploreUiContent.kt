package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.utils.override
import com.sottti.roller.coasters.presentation.utils.plus

@Composable
internal fun ExploreContent(
    filters: Filters,
    lazyListState: LazyListState,
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    outerPadding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ExploreTopBar(
                filters = filters,
                lazyListState = lazyListState,
                onAction = onAction,
                onNavigateToSettings = onNavigateToSettings,
            )
        },
    ) { innerPadding ->
        RollerCoasters(
            listState = lazyListState,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = innerPadding.override(bottom = outerPadding.calculateBottomPadding()),
            rollerCoasters = rollerCoasters,
        )
    }
}

@Composable
private fun RollerCoasters(
    listState: LazyListState,
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (rollerCoasters.loadState.refresh) {
            is Loading -> FillMaxWidthProgressIndicator(padding)
            is LoadState.Error -> ErrorUi(
                modifier = Modifier.padding(padding),
                button = ErrorButton(onClick = {})
            )

            is NotLoading -> {
                when (rollerCoasters.itemCount) {
                    0 -> EmptyUi(modifier = Modifier.padding(padding))
                    else -> LoadedRollerCoasters(
                        listState = listState,
                        onAction = onAction,
                        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                        padding = padding,
                        rollerCoasters = rollerCoasters,
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadedRollerCoasters(
    listState: LazyListState,
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    LazyColumn(
        state = listState,
        contentPadding = padding + PaddingValues(dimensions.padding.medium),
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
                    onAction = onAction,
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
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoaster: ExploreRollerCoaster,
) {
    RollerCoasterCard.Large(
        imageUrl = rollerCoaster.imageUrl,
        modifier = Modifier.fillMaxWidth(),
        parkName = rollerCoaster.parkName,
        rollerCoasterName = rollerCoaster.rollerCoasterName,
        stat = rollerCoaster.stat,
        statDetail = rollerCoaster.statDetail,
        onClick = { onNavigateToRollerCoaster(rollerCoaster.id) },
    )
}

@Composable
private fun FillMaxWidthProgressIndicator(
    padding: PaddingValues = PaddingValues(vertical = dimensions.padding.medium),
) {
    ProgressIndicator(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
    )
}
