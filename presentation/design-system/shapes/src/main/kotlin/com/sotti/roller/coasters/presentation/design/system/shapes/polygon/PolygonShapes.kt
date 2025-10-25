package com.sotti.roller.coasters.presentation.design.system.shapes.polygon

import androidx.compose.runtime.Immutable
import androidx.graphics.shapes.RoundedPolygon

@Immutable
public data class PolygonShapes(
    public val hexagon: RoundedPolygon,
    public val octagon: RoundedPolygon,
    public val decagon: RoundedPolygon,
)
