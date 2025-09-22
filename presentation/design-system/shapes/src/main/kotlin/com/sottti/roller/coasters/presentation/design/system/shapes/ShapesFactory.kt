package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions

@Composable
internal fun shapes(): Shapes {
    return Shapes(
        roundedCorner = RoundedCornerShapes(
            extraSmall = dimensions.cornerRadii.extraSmall,
            small = dimensions.cornerRadii.small,
            medium = dimensions.cornerRadii.medium,
            large = dimensions.cornerRadii.large,
            extraLarge = dimensions.cornerRadii.extraLarge,
        ),
    )
}
