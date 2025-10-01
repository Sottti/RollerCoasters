package com.sottti.roller.coasters.presentation.design.system.informative

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

@Immutable
internal data class InformativeState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
    val buttonText: Int?,
)
