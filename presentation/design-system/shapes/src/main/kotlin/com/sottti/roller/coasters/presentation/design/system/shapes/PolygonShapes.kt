package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

internal val hexagon = RoundedPolygon(
    numVertices = 6,
    rounding = CornerRounding(0.2f),
)
internal val octagon = RoundedPolygon(
    numVertices = 8,
    rounding = CornerRounding(0.2f),
)

internal val decagon = RoundedPolygon(
    numVertices = 10,
    rounding = CornerRounding(0.2f),
)
