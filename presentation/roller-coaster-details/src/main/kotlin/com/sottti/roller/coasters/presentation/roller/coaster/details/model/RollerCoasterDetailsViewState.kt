package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class RollerCoasterDetailsViewState(
    val identity: RollerCoasterIdentityViewState,
    val status: RollerCoasterStatusViewState,
)