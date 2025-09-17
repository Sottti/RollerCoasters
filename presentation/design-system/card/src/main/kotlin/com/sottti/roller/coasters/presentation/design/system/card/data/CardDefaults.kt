package com.sottti.roller.coasters.presentation.design.system.card.data

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.sottti.roller.coasters.presentation.design.system.card.model.CornerType
import com.sottti.roller.coasters.presentation.design.system.card.model.Corners

public object CardDefaults {
    public val cardConvexCorners: Corners = Corners(
        bottomEnd = CornerType.Convex,
        bottomStart = CornerType.Convex,
        topEnd = CornerType.Convex,
        topStart = CornerType.Convex,
    )

    public val cardConcaveCorners: Corners =
        Corners(
            bottomEnd = CornerType.Concave,
            bottomStart = CornerType.Concave,
            topEnd = CornerType.Concave,
            topStart = CornerType.Concave,
        )

    public val cardSharpCorners: Corners =
        Corners(
            bottomEnd = CornerType.Sharp,
            bottomStart = CornerType.Sharp,
            topEnd = CornerType.Sharp,
            topStart = CornerType.Sharp,
        )

    @Composable
    internal fun defaultCornerRadius(
        base: CornerBasedShape = MaterialTheme.shapes.large,
    ): Dp {
        val density = LocalDensity.current
        val topStart = base.topStart
        val px = topStart.toPx(
            shapeSize = Size(
                width = 1000f, // large enough so % values resolve sanely
                height = 1000f // large enough so % values resolve sanely
            ),
            density = density,
        )
        val radius: Dp = with(density) { px.toDp() }

        return radius
    }
}
