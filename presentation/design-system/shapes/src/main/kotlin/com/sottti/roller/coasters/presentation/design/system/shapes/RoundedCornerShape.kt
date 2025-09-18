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
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner

@Immutable
internal class RoundedCornerShape(
    private val bottomEnd: Corner,
    private val bottomStart: Corner,
    private val topEnd: Corner,
    private val topStart: Corner,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val rect = Rect(0f, 0f, size.width, size.height)
        val corners = listOf(topStart, topEnd, bottomEnd, bottomStart)
        val radii = corners.map { convexCornerRadius(it, size, density) }
        val hasConvex = radii.any { it != CornerRadius.Zero }
        val hasConcave = corners.any { it is Corner.Concave }

        // Fast path: rectangle if no convex or concave corners
        if (!hasConvex && !hasConcave) return Outline.Rectangle(rect)

        // Fast path: rounded rectangle if no concave corners
        if (!hasConcave) {
            return Outline.Rounded(
                RoundRect(
                    rect = rect,
                    topLeft = radii[0],
                    topRight = radii[1],
                    bottomRight = radii[2],
                    bottomLeft = radii[3],
                )
            )
        }

        // Handle concave corners with path subtraction
        val concaveRadii = corners.map { concaveRadiusInPixels(it, size, density) }
        val hasNonZeroConcave = concaveRadii.any { it > 0f }

        // If no effective concave radii, fall back to rounded or rectangle
        if (!hasNonZeroConcave) {
            return if (hasConvex) {
                Outline.Rounded(
                    RoundRect(
                        rect = rect,
                        topLeft = radii[0],
                        topRight = radii[1],
                        bottomRight = radii[2],
                        bottomLeft = radii[3],
                    )
                )
            } else {
                Outline.Rectangle(rect)
            }
        }

        // Build base path (rounded if convex, else rectangle)
        val basePath = Path().apply {
            when {
                hasConvex -> addRoundRect(RoundRect(rect, radii[0], radii[1], radii[2], radii[3]))
                else -> addRect(rect)
            }
        }

        // Build concave cutouts
        val cutoutPath = Path().apply {
            concaveRadii.forEachIndexed { index, radius ->
                if (radius > 0f) {
                    addConcaveOval(
                        position = CornerPosition.values()[index],
                        radius = radius,
                        size = size,
                        layoutDirection = layoutDirection,
                    )
                }
            }
        }

        // Subtract cutouts from base
        return Outline.Generic(
            Path.combine(PathOperation.Difference, basePath, cutoutPath)
        )
    }

    private fun convexCornerRadius(corner: Corner, size: Size, density: Density): CornerRadius =
        when (corner) {
            is Corner.Convex -> {
                val radius = corner.cornerSize.toPx(size, density)
                if (radius > 0f) CornerRadius(radius) else CornerRadius.Zero
            }
            else -> CornerRadius.Zero
        }

    private fun concaveRadiusInPixels(corner: Corner, size: Size, density: Density): Float =
        if (corner is Corner.Concave) corner.cornerSize.toPx(size, density) else 0f

    private fun Path.addConcaveOval(
        position: CornerPosition,
        radius: Float,
        size: Size,
        layoutDirection: LayoutDirection,
    ) {
        val (centerX, centerY) = when (position) {
            CornerPosition.TopStart -> if (layoutDirection == LayoutDirection.Rtl) size.width to 0f else 0f to 0f
            CornerPosition.TopEnd -> if (layoutDirection == LayoutDirection.Rtl) 0f to 0f else size.width to 0f
            CornerPosition.BottomStart -> if (layoutDirection == LayoutDirection.Rtl) size.width to size.height else 0f to size.height
            CornerPosition.BottomEnd -> if (layoutDirection == LayoutDirection.Rtl) 0f to size.height else size.width to size.height
        }
        val ovalRect = Rect(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        // Use addOval if available; otherwise, fall back to arcTo for compatibility
        try {
            addOval(ovalRect)
        } catch (e: NoSuchMethodError) {
            // Fallback for older Compose versions or environments missing addOval
            arcTo(
                rect = ovalRect,
                startAngleDegrees = 0f,
                sweepAngleDegrees = 360f,
                forceMoveTo = false
            )
        }
    }

    private enum class CornerPosition { TopStart, TopEnd, BottomEnd, BottomStart }
}
