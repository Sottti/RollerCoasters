package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

internal fun CacheDrawScope.square(): RoundedPolygon =
    RoundedPolygon(
        numVertices = 4,
        radius = size.minDimension / 2f,
        centerX = size.width / 2f,
        centerY = size.height / 2f,
        rounding = CornerRounding(size.minDimension / 10f, smoothing = 0.1f)
    )

internal fun CacheDrawScope.triangle(): RoundedPolygon =
    RoundedPolygon(
        numVertices = 3,
        radius = size.minDimension / 2f,
        centerX = size.width / 2f,
        centerY = size.height / 2f,
        rounding = CornerRounding(size.minDimension / 10f, smoothing = 0.1f)
    )

internal fun CacheDrawScope.roundedPolygon() =
    RoundedPolygon(
        centerX = size.width / 2,
        centerY = size.height / 2,
        numVertices = 6,
        radius = size.minDimension / 2,
        rounding = CornerRounding(0.2f),
    )

@Composable
internal fun hexagonRoundedPolygon(): RoundedPolygon {
    val radius = with(LocalDensity.current) { 16.dp.toPx() }
    return RoundedPolygon(
        numVertices = 6,
        radius = radius,
        rounding = CornerRounding(0.2f),
    )
}

@Composable
internal fun hexagonRoundedPolygonShape() =
    RoundedPolygonShape(polygon = hexagonRoundedPolygon())
