package com.sottti.roller.coasters.presentation.top.bars.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Immutable

@Immutable
@OptIn(ExperimentalMaterial3Api::class)
internal data class MainTopBarState(
    val onNavigateToSettings: () -> Unit,
    val scrollBehavior: TopAppBarScrollBehavior? = null,
    val showTitle: Boolean = false,
    val titleResId: Int? = null,
)
