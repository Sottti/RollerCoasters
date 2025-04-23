package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class TopBarState(
    @StringRes val title: Int?,
    val icon: IconState,
)