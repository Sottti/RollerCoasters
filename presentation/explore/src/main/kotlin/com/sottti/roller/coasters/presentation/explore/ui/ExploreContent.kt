package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.Filters

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
        val rememberedPaddingValues = remember(innerPadding, outerPadding) {
            PaddingValues(
                start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                top = innerPadding.calculateTopPadding(),
                bottom = outerPadding.calculateBottomPadding(),
            )
        }
        RollerCoastersList(
            listState = lazyListState,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            padding = rememberedPaddingValues,
            rollerCoasters = rollerCoasters,
        )
    }
}

@Composable
private fun RollerCoastersList(
    listState: LazyListState,
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    Column(
        modifier = Modifier
            .padding(padding)
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
                        onAction = onAction,
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
    onAction: (ExploreAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(dimensions.padding.medium),
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