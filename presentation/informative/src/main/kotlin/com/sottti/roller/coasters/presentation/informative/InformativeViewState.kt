package com.sottti.roller.coasters.presentation.informative

import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

internal data class InformativeViewState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
    val buttonText: Int,
)