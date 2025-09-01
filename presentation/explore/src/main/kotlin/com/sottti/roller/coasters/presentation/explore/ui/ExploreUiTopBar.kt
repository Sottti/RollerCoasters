package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.top.bars.ui.MainTopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ExploreTopBar(
    filters: Filters?,
    lazyListState: LazyListState,
    onAction: (ExploreAction) -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    val containerColor = TopAppBarDefaults.topAppBarColors().containerColor
    val scrolledContainerColor = TopAppBarDefaults.topAppBarColors().scrolledContainerColor
    val isScrolled = lazyListState.firstVisibleItemScrollOffset > 0
    val backgroundColor by animateColorAsState(
        targetValue = if (isScrolled) scrolledContainerColor else containerColor,
        label = "expandable top bar background color animation",
    )
    Column(modifier = Modifier.background(backgroundColor)) {
        MainTopBar(onNavigateToSettings = onNavigateToSettings)
        filters?.let { FilterChips(filters = filters, onAction = onAction) }
    }
}