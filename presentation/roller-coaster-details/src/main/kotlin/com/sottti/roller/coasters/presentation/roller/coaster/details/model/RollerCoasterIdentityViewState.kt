package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class RollerCoasterIdentityViewState(
    @StringRes val header : Int,
    val formerNames: RollerCoasterDetailsRow?,
    val name: RollerCoasterDetailsRow,
)