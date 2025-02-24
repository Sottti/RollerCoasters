package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.chip.Chip
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.explore.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FilterChips(listState: LazyListState) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium)
    ) {
        TypeChip()
    }
}

@Composable
private fun TypeChip() {
    Chip(
        labelResId = R.string.chip_label_type,
        selected = false,
        expanded = false,
        onClick = {},
    )
}