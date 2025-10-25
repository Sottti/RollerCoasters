package com.sotti.roller.coasters.presentation.design.system.error

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

@Immutable
internal data class ErrorState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
    val buttonText: Int,
)
