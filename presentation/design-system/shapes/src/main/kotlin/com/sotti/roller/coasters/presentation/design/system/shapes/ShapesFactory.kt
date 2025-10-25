package com.sotti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sotti.roller.coasters.presentation.design.system.shapes.corner.roundedCornerShapes
import com.sotti.roller.coasters.presentation.design.system.shapes.polygon.roundedPolygonShapes

@Composable
@ReadOnlyComposable
internal fun shapes(): Shapes =
    Shapes(
        roundedCorner = roundedCornerShapes(),
        roundedPolygon = roundedPolygonShapes(),
    )
