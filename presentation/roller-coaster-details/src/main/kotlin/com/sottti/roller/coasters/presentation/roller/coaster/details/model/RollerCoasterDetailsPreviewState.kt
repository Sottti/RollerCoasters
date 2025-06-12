package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class RollerCoasterDetailsPreviewState(
    val onAction: (RollerCoasterDetailsAction) -> Unit,
    val onBackNavigation: () -> Unit,
    val state: RollerCoasterDetailsState,
)