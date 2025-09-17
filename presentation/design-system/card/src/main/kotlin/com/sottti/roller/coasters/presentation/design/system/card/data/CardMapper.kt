package com.sottti.roller.coasters.presentation.design.system.card.data

import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.sottti.roller.coasters.presentation.design.system.card.RoundedCornerShape
import com.sottti.roller.coasters.presentation.design.system.card.model.CornerType
import com.sottti.roller.coasters.presentation.design.system.card.model.Corners
import androidx.compose.foundation.shape.RoundedCornerShape as MaterialRoundedCornerShape

@Composable
internal fun Corners.toShape(): Shape = when {
    topStart == CornerType.Concave ||
            topEnd == CornerType.Concave ||
            bottomStart == CornerType.Concave ||
            bottomEnd == CornerType.Concave ->
        RoundedCornerShape(
            radius = CardDefaults.defaultCornerRadius(),
            corners = this,
        )

    else -> MaterialRoundedCornerShape(
        topStart = when (topStart) {
            CornerType.Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.topStart
        },
        topEnd = when (topEnd) {
            CornerType.Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.topEnd
        },
        bottomStart = when (bottomStart) {
            CornerType.Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.bottomStart
        },
        bottomEnd = when (bottomEnd) {
            CornerType.Sharp -> ZeroCornerSize
            else -> MaterialTheme.shapes.large.bottomEnd
        },
    )
}
