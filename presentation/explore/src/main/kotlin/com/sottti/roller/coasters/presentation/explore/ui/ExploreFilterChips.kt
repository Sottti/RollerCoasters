package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.chip.Chip
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.model.PrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SecondaryFilter
import com.sottti.roller.coasters.presentation.utils.Spacer

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FilterChips(
    filters: Filters,
    onAction: (ExploreAction) -> Unit,
) {
    val rememberedOnAction = rememberUpdatedState(onAction)
    Column(modifier = Modifier.padding(vertical = dimensions.padding.small)) {
        PrimaryFilters(filters.primary, rememberedOnAction.value)
        Spacer(dimensions.padding.small)
        SecondaryFilters(filters.secondary, rememberedOnAction.value)
    }
}

@Composable
private fun PrimaryFilters(
    filters: List<PrimaryFilter>,
    onAction: (ExploreAction) -> Unit,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium),
    ) {
        filters.forEach { filter ->
            key(filter.labelResId) { PrimaryFilterChip(filter = filter, onAction = onAction) }
        }
    }
}

@Composable
private fun SecondaryFilters(
    filters: List<SecondaryFilter>,
    onAction: (ExploreAction) -> Unit,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium)
    ) {
        filters
            .filter { it.visible }
            .forEach { filter -> key(filter.labelResId) { SecondaryFilterChip(onAction, filter) } }
    }
}

@Composable
private fun PrimaryFilterChip(
    filter: PrimaryFilter,
    onAction: (ExploreAction) -> Unit,
) {
    Chip(
        expanded = filter.expanded,
        labelResId = filter.labelResId,
        leadingIcon = filter.leadingIcon,
        onClick = { onAction(filter.action) },
        selected = filter.selected,
    )
}

@Composable
private fun SecondaryFilterChip(
    onAction: (ExploreAction) -> Unit,
    filter: SecondaryFilter,
) {
    Chip(
        labelResId = filter.labelResId,
        leadingIcon = filter.leadingIcon,
        onClick = { onAction(filter.action) },
        selected = filter.selected,
    )
}