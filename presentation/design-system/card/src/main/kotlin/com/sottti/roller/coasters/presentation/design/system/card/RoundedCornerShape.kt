package com.sottti.roller.coasters.presentation.design.system.card

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.sottti.roller.coasters.presentation.design.system.card.model.Corner
import com.sottti.roller.coasters.presentation.design.system.card.model.CornerType
import com.sottti.roller.coasters.presentation.design.system.card.model.Corners
import kotlin.math.min

@Immutable
internal class RoundedCornerShape(
    val corners: Corners,
    val radius: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val radiusPx = with(density) { radius.toPx() }
            .coerceAtMost(min(size.width, size.height))

        if (radiusPx <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }

        val rectPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(left = 0f, top = 0f, right = size.width, bottom = size.height),
                    topLeft = corners.topStart.cornerRadius(radiusPx),
                    topRight = corners.topEnd.cornerRadius(radiusPx),
                    bottomRight = corners.bottomEnd.cornerRadius(radiusPx),
                    bottomLeft = corners.bottomStart.cornerRadius(radiusPx),
                )
            )
        }
        val combinedPath = Path.combine(
            operation = PathOperation.Difference,
            path1 = rectPath,
            path2 = corners.toCutoutPath(layoutDirection, radiusPx, size)
        )
        return Outline.Generic(combinedPath)
    }
}

private fun CornerType.cornerRadius(radiusPx: Float): CornerRadius = when (this) {
    CornerType.Convex -> CornerRadius(x = radiusPx, y = radiusPx)
    else -> CornerRadius.Zero
}

private fun Corners.toCutoutPath(
    layoutDirection: LayoutDirection,
    radiusPx: Float,
    size: Size,
): Path = Path().apply {
    for (corner in concaveCorners()) {
        addOval(
            corner = corner,
            layoutDirection = layoutDirection,
            radiusPx = radiusPx,
            size = size
        )
    }
}

private fun Corners.concaveCorners(): List<Corner> = buildList {
    if (topStart == CornerType.Concave) add(Corner.TopStart)
    if (topEnd == CornerType.Concave) add(Corner.TopEnd)
    if (bottomStart == CornerType.Concave) add(Corner.BottomStart)
    if (bottomEnd == CornerType.Concave) add(Corner.BottomEnd)
}

private fun Path.addOval(
    corner: Corner,
    layoutDirection: LayoutDirection,
    radiusPx: Float,
    size: Size,
) {
    val (cx, cy) = cornerCenter(corner, size, layoutDirection)
    addOval(Rect(cx - radiusPx, cy - radiusPx, cx + radiusPx, cy + radiusPx))
}

private fun cornerCenter(
    corner: Corner,
    size: Size,
    layoutDirection: LayoutDirection,
): Pair<Float, Float> {
    val isRtl = layoutDirection == LayoutDirection.Rtl
    val startXCoordinate = if (isRtl) size.width else 0f
    val endXCoordinate = if (isRtl) 0f else size.width
    return when (corner) {
        Corner.TopStart -> startXCoordinate to 0f
        Corner.TopEnd -> endXCoordinate to 0f
        Corner.BottomStart -> startXCoordinate to size.height
        Corner.BottomEnd -> endXCoordinate to size.height
    }
}
