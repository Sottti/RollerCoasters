package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable
import androidx.graphics.shapes.RoundedPolygon

@Immutable
public data class Shapes(
    public val roundedCorner: RoundedCornerShapes,
    public val roundedPolygon : RoundedPolygonShapes,
)

@Immutable
public data class RoundedCornerShapes(
    public val extraSmall: CornerBasedShape,
    public val small: CornerBasedShape,
    public val medium: CornerBasedShape,
    public val large: CornerBasedShape,
    public val extraLarge: CornerBasedShape,
)
@Immutable
public data class RoundedPolygonShapes(
    public val hexagon: RoundedPolygon,
    public val octagon: RoundedPolygon,
    public val decagon: RoundedPolygon,
)
