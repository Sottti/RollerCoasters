package com.sottti.roller.coasters.presentation.about.me.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
internal data class AboutMePreviewState(
    val onAction: (AboutMeAction) -> Unit,
    val onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    val onNavigateToSettings: () -> Unit,
    val state: AboutMeState,
)