package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.shapes.corner.RoundedCornerShapes
import com.sottti.roller.coasters.presentation.design.system.shapes.polygon.PolygonShapes

@Immutable
public data class Shapes(
    public val roundedCorner: RoundedCornerShapes,
    public val roundedPolygon: PolygonShapes,
)
