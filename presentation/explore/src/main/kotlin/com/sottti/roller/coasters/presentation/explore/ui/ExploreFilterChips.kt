package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.chip.Chip
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FilterChips(
    filters: Filters,
    listState: LazyListState,
    onAction: (ExploreAction) -> Unit,
) {
    Column {
        PrimaryFilters(filters.primary, onAction)
        SecondaryFilters(filters.secondary, onAction)
    }
}

@Composable
private fun PrimaryFilters(
    filters: List<PrimaryFilter>,
    onAction: (ExploreAction) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium)
    ) {
        filters.forEach { filter -> PrimaryFilterChip(filter = filter, onAction = onAction) }
    }
}

@Composable
private fun SecondaryFilters(
    filters: List<SecondaryFilter>,
    onAction: (ExploreAction) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium)
    ) {
        filters.forEach { filter -> SecondaryFilterChip(onAction, filter) }
    }
}

@Composable
private fun PrimaryFilterChip(
    filter: PrimaryFilter,
    onAction: (ExploreAction) -> Unit,
) {
    Chip(
        labelResId = filter.labelResId,
        selected = filter.selected,
        expanded = filter.expanded,
        onClick = { onAction(filter.action) },
    )
}

@Composable
private fun SecondaryFilterChip(
    onAction: (ExploreAction) -> Unit,
    filter: SecondaryFilter,
) {
    if (filter.visible) {
        Chip(
            labelResId = filter.labelResId,
            selected = filter.selected,
            onClick = { onAction(filter.action) },
        )
    }
}