package com.sotti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.shapes.corner.RoundedCornerShapes
import com.sotti.roller.coasters.presentation.design.system.shapes.polygon.PolygonShapes

@Immutable
public data class Shapes(
    public val roundedCorner: RoundedCornerShapes,
    public val roundedPolygon: PolygonShapes,
)
