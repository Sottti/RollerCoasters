package com.sottti.roller.coasters.presentation.design.system.shapes

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
import androidx.compose.ui.unit.LayoutDirection
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape.CornerPosition.BottomEnd
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape.CornerPosition.BottomStart
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape.CornerPosition.TopEnd
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape.CornerPosition.TopStart
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner

/**
 * A shape with per-corner types: Convex (rounded), Concave (inward cut), or Sharp (90º).
 * Optimized for fast paths (rectangle, rounded) and uses path subtraction only for concave corners.
 */
@Immutable
internal class RoundedCornerShape(
    private val topStart: Corner,
    private val topEnd: Corner,
    private val bottomEnd: Corner,
    private val bottomStart: Corner,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val rect = Rect(0f, 0f, size.width, size.height)
        val corners = listOf(topStart, topEnd, bottomEnd, bottomStart)
        val radii = corners.map { convexCornerRadius(it, density, size) }
        val hasConvex = radii.any { it != CornerRadius.Zero }
        val hasConcave = corners.any { it is Corner.Concave }

        // Fast path: rectangle if no convex or concave corners
        if (!hasConvex && !hasConcave) return Outline.Rectangle(rect)

        // Fast path: rounded rectangle if no concave corners
        if (!hasConcave) return Outline.Rounded(rect.toRoundRect(radii))

        // Handle concave corners
        val concaveRadii = corners.map { concaveRadiusInPixels(it, density, size) }
        if (!concaveRadii.any { it > 0f }) {
            // No effective concave radii: return rounded or rectangle
            return when {
                hasConvex -> Outline.Rounded(rect.toRoundRect(radii))
                else -> Outline.Rectangle(rect)
            }
        }

        // Build base path (rounded if convex, else rectangle)
        val basePath = Path().apply {
            if (hasConvex) addRoundRect(rect.toRoundRect(radii)) else addRect(rect)
        }

        // Build concave cutouts
        val cutoutPath = Path().apply {
            concaveRadii.forEachIndexed { index, radius ->
                if (radius > 0f) {
                    addConcaveOval(CornerPosition.entries[index], radius, size, layoutDirection)
                }
            }
        }

        // Subtract cutouts from base
        return Outline.Generic(Path.combine(PathOperation.Difference, basePath, cutoutPath))
    }

    private fun convexCornerRadius(corner: Corner, density: Density, size: Size): CornerRadius =
        when (corner) {
            is Corner.Convex -> {
                val radius = corner.cornerSize.toPx(size, density)
                if (radius > 0f) CornerRadius(radius) else CornerRadius.Zero
            }

            else -> CornerRadius.Zero
        }

    private fun concaveRadiusInPixels(corner: Corner, density: Density, size: Size): Float =
        if (corner is Corner.Concave) corner.cornerSize.toPx(size, density) else 0f

    private fun Path.addConcaveOval(
        position: CornerPosition,
        radius: Float,
        size: Size,
        layoutDirection: LayoutDirection,
    ) {
        val (centerX, centerY) = when (position) {
            TopStart -> if (layoutDirection == LayoutDirection.Rtl) size.width to 0f else 0f to 0f
            TopEnd -> if (layoutDirection == LayoutDirection.Rtl) 0f to 0f else size.width to 0f
            BottomStart -> if (layoutDirection == LayoutDirection.Rtl) size.width to size.height else 0f to size.height
            BottomEnd -> if (layoutDirection == LayoutDirection.Rtl) 0f to size.height else size.width to size.height
        }
        val ovalRect = Rect(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        addOval(ovalRect)
    }

    private enum class CornerPosition { TopStart, TopEnd, BottomEnd, BottomStart }
}

/**
 * Converts a Rect to a RoundRect using a list of four CornerRadius values in order:
 * topStart, topEnd, bottomEnd, bottomStart.
 */
private fun Rect.toRoundRect(radii: List<CornerRadius>): RoundRect {
    require(radii.size == 4) { "Radii list must contain exactly four elements" }
    return RoundRect(
        rect = this,
        topLeft = radii[0],
        topRight = radii[1],
        bottomRight = radii[2],
        bottomLeft = radii[3],
    )
}
