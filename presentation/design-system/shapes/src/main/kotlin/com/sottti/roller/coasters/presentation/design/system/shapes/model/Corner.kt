package com.sottti.roller.coasters.presentation.design.system.shapes.model

import androidx.compose.foundation.shape.CornerSize

public sealed interface Corner {
    public data class Concave(val cornerSize: CornerSize) : Corner
    public data class Convex(val cornerSize: CornerSize) : Corner
    public data object Sharp : Corner
}
