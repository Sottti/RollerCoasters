package com.sottti.roller.coasters.presentation.design.system.shapes.data

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.sottti.roller.coasters.presentation.design.system.shapes.CornersShape
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Convex
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Cut
import com.sottti.roller.coasters.presentation.design.system.shapes.model.Corner.Sharp

@Composable
public fun cornersShape(
    bottomEnd: Corner = Convex(MaterialTheme.shapes.large.bottomEnd),
    bottomStart: Corner = Convex(MaterialTheme.shapes.large.bottomStart),
    topEnd: Corner = Convex(MaterialTheme.shapes.large.topEnd),
    topStart: Corner = Convex(MaterialTheme.shapes.large.topStart),
): Shape {
    val corners = listOf(bottomEnd, bottomStart, topEnd, topStart)
    return when {
        corners.all { corner -> corner is Sharp } -> RectangleShape

        corners.all { corner -> corner is Convex || corner is Sharp } ->
            RoundedCornerShape(
                bottomEnd = bottomEnd.cornerSize,
                bottomStart = bottomStart.cornerSize,
                topEnd = topEnd.cornerSize,
                topStart = topStart.cornerSize,
            )

        corners.all { corner -> corner is Cut } ->
            CutCornerShape(
                bottomEnd = bottomEnd.cornerSize,
                bottomStart = bottomStart.cornerSize,
                topEnd = topEnd.cornerSize,
                topStart = topStart.cornerSize,
            )

        else -> CornersShape(
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            topEnd = topEnd,
            topStart = topStart,
        )
    }
}
