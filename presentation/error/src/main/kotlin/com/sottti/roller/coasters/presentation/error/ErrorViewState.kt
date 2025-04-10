package com.sottti.roller.coasters.presentation.error

import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

internal data class ErrorViewState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
    val buttonText: Int,
)