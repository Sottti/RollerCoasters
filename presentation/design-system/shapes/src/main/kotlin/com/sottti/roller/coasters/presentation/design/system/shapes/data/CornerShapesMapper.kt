package com.sottti.roller.coasters.presentation.design.system.shapes.data

import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Concave
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Convex
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Sharp
import androidx.compose.foundation.shape.RoundedCornerShape as MaterialRoundedCornerShape

@Composable
public fun cornerShapes(
    bottomEnd: Corner = Convex(MaterialTheme.shapes.large.bottomEnd),
    bottomStart: Corner = Convex(MaterialTheme.shapes.large.bottomStart),
    topEnd: Corner = Convex(MaterialTheme.shapes.large.topEnd),
    topStart: Corner = Convex(MaterialTheme.shapes.large.bottomStart),
): Shape = when {
    topStart is Concave || topEnd is Concave ||
        bottomStart is Concave || bottomEnd is Concave ->
        RoundedCornerShape(
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            topEnd = topEnd,
            topStart = topStart,
        )

    else -> MaterialRoundedCornerShape(
        topStart = when (topStart) {
            Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.topStart
        },
        topEnd = when (topEnd) {
            Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.topEnd
        },
        bottomStart = when (bottomStart) {
            Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.bottomStart
        },
        bottomEnd = when (bottomEnd) {
            Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.bottomEnd
        },
    )
}
