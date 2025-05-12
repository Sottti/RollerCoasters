package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.cuvva.presentation.design.system.icons.model.IconState
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState.Loaded
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.FavouriteIconState.Loading
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

@Composable
@ExperimentalMaterial3Api
internal fun TopBar(
    onBackNavigation: () -> Unit,
    onToggleFavourite: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: TopBarState,
) {
    TopAppBar(
        title = { state.title?.let { Text.Vanilla(state.title) } },
        scrollBehavior = scrollBehavior,
        navigationIcon = { navigationIcon(state.navigationIcon, onBackNavigation) },
        actions = {
            favouriteIcon(
                state = state.favouriteIcon,
                onToggleFavourite = onToggleFavourite
            )
        }
    )
}

@Composable
private fun navigationIcon(
    state: IconState,
    onBackNavigation: () -> Unit,
) {
    Icon(
        state = state,
        onClick = { onBackNavigation() },
    )
}

@Composable
private fun favouriteIcon(
    state: FavouriteIconState,
    onToggleFavourite: () -> Unit,
) {
    when (state) {
        is Loaded -> Icon(state = state.iconState, onClick = onToggleFavourite)
        Loading -> ProgressIndicator(
            modifier = Modifier.padding(end = dimensions.padding.medium),
            size = ProgressIndicatorSize.Small
        )
    }
}