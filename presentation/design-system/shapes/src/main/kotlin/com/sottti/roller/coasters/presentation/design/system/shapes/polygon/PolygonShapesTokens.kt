package com.sottti.roller.coasters.presentation.design.system.shapes.polygon

import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

internal fun roundedPolygonShapes(): PolygonShapes =
    PolygonShapes(
        hexagon = hexagon,
        octagon = octagon,
        decagon = decagon,
    )

private val hexagon = RoundedPolygon(
    numVertices = 6,
    rounding = CornerRounding(radius = 0.2f),
)
private val octagon = RoundedPolygon(
    numVertices = 8,
    rounding = CornerRounding(radius = 0.2f),
)

private val decagon = RoundedPolygon(
    numVertices = 10,
    rounding = CornerRounding(radius = 0.2f),
)
