package com.sottti.roller.coasters.presentation.design.system.shapes.model

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize

public sealed interface Corner {
    public val cornerSize: CornerSize

    public data class Concave(override val cornerSize: CornerSize) : Corner
    public data class Convex(override val cornerSize: CornerSize) : Corner
    public data class Cut(override val cornerSize: CornerSize) : Corner
    public data object Sharp : Corner {
        override val cornerSize: CornerSize = ZeroCornerSize
    }
}
