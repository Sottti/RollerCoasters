package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable

@Immutable
public data class Shapes(
    public val roundedCorner: RoundedCornerShapes,
)

@Immutable
public data class RoundedCornerShapes(
    public val extraSmall: CornerBasedShape,
    public val small: CornerBasedShape,
    public val medium: CornerBasedShape,
    public val large: CornerBasedShape,
    public val extraLarge: CornerBasedShape,
)
