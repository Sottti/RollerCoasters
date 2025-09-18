package com.sottti.roller.coasters.presentation.design.system.shapes.data

import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.sottti.roller.coasters.presentation.design.system.shapes.RoundedCornerShape
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Concave
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Convex
import androidx.compose.foundation.shape.RoundedCornerShape as MaterialRoundedCornerShape

@Composable
public fun cornerShapes(
    bottomEnd: Corner = Convex(MaterialTheme.shapes.large.bottomEnd),
    bottomStart: Corner = Convex(MaterialTheme.shapes.large.bottomStart),
    topEnd: Corner = Convex(MaterialTheme.shapes.large.topEnd),
    topStart: Corner = Convex(MaterialTheme.shapes.large.bottomStart),
): Shape = when {
    listOf(bottomEnd, bottomStart, topEnd, topStart)
        .any { it is Concave } ->
        RoundedCornerShape(
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            topEnd = topEnd,
            topStart = topStart,
        )

    else -> MaterialRoundedCornerShape(
        topStart = when (topStart) {
            is Convex -> topStart.cornerSize
            else -> ZeroCornerSize
        },
        topEnd = when (topEnd) {
            is Convex -> topEnd.cornerSize
            else -> ZeroCornerSize
        },
        bottomStart = when (bottomStart) {
            is Convex -> bottomStart.cornerSize
            else -> ZeroCornerSize
        },
        bottomEnd = when (bottomEnd) {
            is Convex -> bottomEnd.cornerSize
            else -> ZeroCornerSize
        },
    )
}
