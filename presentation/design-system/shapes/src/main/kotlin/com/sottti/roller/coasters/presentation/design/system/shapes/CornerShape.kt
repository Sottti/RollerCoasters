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
import com.sottti.roller.coasters.presentation.design.system.shapes.CornerShape.CornerPosition.BottomEnd
import com.sottti.roller.coasters.presentation.design.system.shapes.CornerShape.CornerPosition.BottomStart
import com.sottti.roller.coasters.presentation.design.system.shapes.CornerShape.CornerPosition.TopEnd
import com.sottti.roller.coasters.presentation.design.system.shapes.CornerShape.CornerPosition.TopStart
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner

/**
 * A shape with per-corner customization: [Convex] (rounded), [Concave] (inward cut),
 * [Cut] (diagonal cut), or [Sharp] (90-degree). Optimizes for simple cases (rectangle or rounded)
 * and uses path subtraction for complex concave or cut corners.
 *
 * @param topStart The corner style for the top-start corner.
 * @param topEnd The corner style for the top-end corner.
 * @param bottomEnd The corner style for the bottom-end corner.
 * @param bottomStart The corner style for the bottom-start corner.
 */
@Immutable
internal class CornerShape(
    private val topStart: Corner,
    private val topEnd: Corner,
    private val bottomEnd: Corner,
    private val bottomStart: Corner,
) : Shape {
    init {
        listOf(topStart, topEnd, bottomEnd, bottomStart).forEach { corner ->
            require(corner.cornerSize.toPx(Size(width = 100f, height = 100f), Density(1f)) >= 0f) {
                "Corner size must be non-negative, but was ${corner.cornerSize} for corner $corner"
            }
        }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        if (size.width <= 0f || size.height <= 0f) return Outline.Rectangle(Rect.Zero)
        val rect = Rect(0f, 0f, size.width, size.height)
        val corners = listOf(topStart, topEnd, bottomEnd, bottomStart)
        val radii = corners.map { convexCornerRadius(it, density, size) }
        val concaveRadii = corners.map { concaveRadiusInPixels(it, density, size) }
        val cutSizes = corners.map { cutSizeInPixels(it, density, size) }

        // Fast path: rectangle if no corners have size
        if (radii.all { it == CornerRadius.Zero } &&
            concaveRadii.all { it == 0f } &&
            cutSizes.all { it == 0f }
        ) {
            return Outline.Rectangle(rect)
        }

        // Fast path: rounded rectangle if only convex corners
        if (concaveRadii.all { it == 0f } && cutSizes.all { it == 0f }) {
            return Outline.Rounded(rect.toRoundRect(radii))
        }

        // Build base path (rounded if convex, else rectangle)
        val basePath = Path().apply {
            if (radii.any { it != CornerRadius.Zero }) {
                addRoundRect(rect.toRoundRect(radii))
            } else {
                addRect(rect)
            }
        }

        // Build concave and cut cutouts
        val cutoutPath = Path().apply {
            corners.forEachIndexed { index, corner ->
                val position = CornerPosition.entries[index]
                when (corner) {
                    is Corner.Concave -> {
                        val radius = concaveRadiusInPixels(corner, density, size)
                        if (radius > 0f) {
                            addConcaveOval(position, radius, size, layoutDirection)
                        }
                    }

                    is Corner.Cut -> {
                        val cutSize = cutSizeInPixels(corner, density, size)
                        if (cutSize > 0f) {
                            addCutTriangle(position, cutSize, size, layoutDirection)
                        }
                    }

                    else -> Unit
                }
            }
        }

        // Return combined path if cutouts exist
        return if (cutoutPath.isEmpty) {
            Outline.Rounded(rect.toRoundRect(radii))
        } else {
            Outline.Generic(Path.combine(PathOperation.Difference, basePath, cutoutPath))
        }
    }

    private fun clampCornerSize(sizeInPx: Float, size: Size): Float =
        minOf(sizeInPx, size.width / 2, size.height / 2)

    private fun convexCornerRadius(corner: Corner, density: Density, size: Size): CornerRadius =
        when (corner) {
            is Corner.Convex -> {
                val radius = clampCornerSize(corner.cornerSize.toPx(size, density), size)
                if (radius > 0f) CornerRadius(radius) else CornerRadius.Zero
            }

            else -> CornerRadius.Zero
        }

    private fun concaveRadiusInPixels(corner: Corner, density: Density, size: Size): Float =
        when (corner) {
            is Corner.Concave -> clampCornerSize(corner.cornerSize.toPx(size, density), size)
            else -> 0f
        }

    private fun cutSizeInPixels(corner: Corner, density: Density, size: Size): Float =
        when (corner) {
            is Corner.Cut -> clampCornerSize(corner.cornerSize.toPx(size, density), size)
            else -> 0f
        }

    private fun Path.addConcaveOval(
        position: CornerPosition,
        radius: Float,
        size: Size,
        layoutDirection: LayoutDirection,
    ) {
        val (centerX, centerY) = position.getCenter(size, layoutDirection == LayoutDirection.Rtl)
        val ovalRect = Rect(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        addOval(ovalRect)
    }

    private fun Path.addCutTriangle(
        position: CornerPosition,
        cutSize: Float,
        size: Size,
        layoutDirection: LayoutDirection,
    ) {
        val (cornerX, cornerY) = position.getCenter(size, layoutDirection == LayoutDirection.Rtl)
        val coords = position.getCutTriangleCoords(cornerX, cornerY, cutSize)
        moveTo(coords[0], coords[1])
        lineTo(coords[2], coords[3])
        lineTo(coords[4], coords[5])
        close()
    }

    private fun CornerPosition.getCutTriangleCoords(
        cornerX: Float,
        cornerY: Float,
        cutSize: Float,
    ): List<Float> {
        val (xSign, ySign) = when (this) {
            TopStart -> 1f to 1f
            TopEnd -> -1f to 1f
            BottomStart -> 1f to -1f
            BottomEnd -> -1f to -1f
        }
        return listOf(
            cornerX - cutSize * xSign,
            cornerY, // x1, y1
            cornerX,
            cornerY + cutSize * ySign, // x2, y2
            cornerX,
            cornerY // x3, y3
        )
    }

    private enum class CornerPosition(val baseX: Float, val baseY: Float) {
        TopStart(0f, 0f),
        TopEnd(1f, 0f),
        BottomEnd(1f, 1f),
        BottomStart(0f, 1f);

        fun getCenter(size: Size, isRtl: Boolean): Pair<Float, Float> {
            val x = if (isRtl) size.width - baseX * size.width else baseX * size.width
            return x to baseY * size.height
        }
    }
}

/**
 * Converts a [Rect] to a [RoundRect] using a list of four [CornerRadius] values in order:
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
