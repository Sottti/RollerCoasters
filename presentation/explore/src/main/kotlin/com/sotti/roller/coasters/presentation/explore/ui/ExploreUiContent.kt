package com.sotti.roller.coasters.presentation.explore.ui

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
import com.sotti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sotti.roller.coasters.presentation.design.system.empty.EmptyUi
import com.sotti.roller.coasters.presentation.design.system.error.ErrorButton
import com.sotti.roller.coasters.presentation.design.system.error.ErrorUi
import com.sotti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sotti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard
import com.sotti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCardStat
import com.sotti.roller.coasters.presentation.explore.model.ExploreAction
import com.sotti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sotti.roller.coasters.presentation.explore.model.Filters
import com.sotti.roller.coasters.presentation.utils.override

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
            val showFilters = rollerCoasters.itemCount > 0
            ExploreTopBar(
                filters = filters.takeIf { showFilters },
                lazyListState = lazyListState,
                onAction = onAction,
                onNavigateToSettings = onNavigateToSettings,
            )
        },
    ) { innerPadding ->
        RollerCoasters(
            listState = lazyListState,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = innerPadding.override(bottom = outerPadding.calculateBottomPadding()),
            rollerCoasters = rollerCoasters,
        )
    }
}

@Composable
private fun RollerCoasters(
    listState: LazyListState,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (rollerCoasters.loadState.refresh) {
            is Loading -> FillMaxSizeProgressIndicator(padding)
            is LoadState.Error -> ErrorUi(
                modifier = Modifier.padding(padding),
                button = ErrorButton(onClick = {})
            )

            is NotLoading -> {
                when (rollerCoasters.itemCount) {
                    0 -> EmptyUi(modifier = Modifier.padding(padding))
                    else -> LoadedRollerCoasters(
                        listState = listState,
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
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    LazyColumn(
        state = listState,
        contentPadding = padding + PaddingValues(dimensions.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
    ) {
        if (rollerCoasters.loadState.prepend is Loading) {
            item(key = "loading") { FillMaxWidthProgressIndicator() }
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
            item(key = "loading") { FillMaxWidthProgressIndicator() }
        }
    }
}

@Composable
private fun RollerCoaster(
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoaster: ExploreRollerCoaster,
) {
    RollerCoasterCard.Large(
        imageUrl = rollerCoaster.imageUrl,
        modifier = Modifier.fillMaxWidth(),
        parkName = rollerCoaster.parkName,
        rollerCoasterName = rollerCoaster.rollerCoasterName,
        stat = rollerCoaster.stat?.let {
            RollerCoasterCardStat(
                value = rollerCoaster.stat,
                detail = rollerCoaster.statDetail,
            )
        },
        onClick = { onNavigateToRollerCoaster(rollerCoaster.id) },
    )
}

@Composable
private fun FillMaxSizeProgressIndicator(
    padding: PaddingValues = PaddingValues(vertical = dimensions.spacing.medium),
) {
    ProgressIndicator(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
    )
}

@Composable
private fun FillMaxWidthProgressIndicator(
    padding: PaddingValues = PaddingValues(vertical = dimensions.spacing.medium),
) {
    ProgressIndicator(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
    )
}
