package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class RollerCoasterStatusViewState(
    @StringRes val header : Int,
    val closedDate: RollerCoasterDetailsRow?,
    val current: RollerCoasterDetailsRow,
    val former: RollerCoasterDetailsRow?,
    val openedDate: RollerCoasterDetailsRow?,
)