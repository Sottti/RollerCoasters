package com.sottti.roller.coasters.presentation.error

import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

internal data class ErrorState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
    val buttonText: Int,
)
