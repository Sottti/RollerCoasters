package com.sottti.roller.coasters.presentation.search.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable

@Immutable
internal data class SearchPreviewState(
    val onAction: (SearchAction) -> Unit,
    val onNavigateToRollerCoaster: (Int) -> Unit,
    val onNavigateToSettings: () -> Unit,
    val paddingValues: PaddingValues,
    val state: SearchViewState,
)
