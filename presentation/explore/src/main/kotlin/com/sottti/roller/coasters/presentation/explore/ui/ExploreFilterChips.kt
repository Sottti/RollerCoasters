package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import com.sottti.roller.coasters.presentation.explore.model.Filter.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.Filter.SecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.Filters

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FilterChips(
    filters: Filters,
    listState: LazyListState,
    onAction: (ExploreAction) -> Unit,
) {
    Column {
        PrimaryFilters(filters.primaryFilters, onAction)
        SecondaryFilters(filters.secondaryFilters, onAction)
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
        filters.forEach { filter ->
            when (filter) {
                else -> PrimaryFilterChip(onAction, filter)
            }
        }
    }
}

@Composable
private fun SecondaryFilters(
    filters: List<SecondaryFilter>?,
    onAction: (ExploreAction) -> Unit
) {
    val isVisible = filters?.isNotEmpty() == true

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(300))
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium)
    ) {
        if (isVisible) {
            filters.forEach { filter -> SecondaryFilterChip(onAction, filter) }
        }
    }
}

@Composable
private fun PrimaryFilterChip(
    onAction: (ExploreAction) -> Unit,
    filter: PrimaryFilter,
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
    Chip(
        labelResId = filter.labelResId,
        selected = filter.selected,
        onClick = { onAction(filter.action) },
    )
}