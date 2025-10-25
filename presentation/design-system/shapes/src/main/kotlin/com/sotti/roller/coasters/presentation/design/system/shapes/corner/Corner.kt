package com.sotti.roller.coasters.presentation.design.system.shapes.corner

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.ui.unit.Dp

public sealed interface Corner {
    public val cornerSize: CornerSize

    public data class Concave(override val cornerSize: CornerSize) : Corner
    public data class Rounded(override val cornerSize: CornerSize) : Corner
    public data class Cut(override val cornerSize: CornerSize) : Corner
    public data object Sharp : Corner {
        override val cornerSize: CornerSize = ZeroCornerSize
    }

    public companion object {
        public fun rounded(size: Dp): Corner = Rounded(CornerSize(size))
        public fun cut(size: Dp): Corner = Cut(CornerSize(size))
        public fun concave(size: Dp): Corner = Concave(CornerSize(size))
    }
}
