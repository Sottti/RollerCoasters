package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions

@Composable
@ReadOnlyComposable
internal fun shapes(): Shapes =
    Shapes(
        roundedCorner = roundedCornerShapes(),
        roundedPolygon = roundedPolygonShapes(),
    )

@Composable
@ReadOnlyComposable
private fun roundedCornerShapes(): RoundedCornerShapes = RoundedCornerShapes(
    extraSmall = dimensions.cornerRadii.extraSmall,
    small = dimensions.cornerRadii.small,
    medium = dimensions.cornerRadii.medium,
    large = dimensions.cornerRadii.large,
    extraLarge = dimensions.cornerRadii.extraLarge,
)

private fun roundedPolygonShapes(): RoundedPolygonShapes =
    RoundedPolygonShapes(
        hexagon = hexagon,
        octagon = octagon,
        decagon = decagon,
    )
