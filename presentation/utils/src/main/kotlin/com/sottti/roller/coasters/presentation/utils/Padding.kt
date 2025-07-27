package com.sottti.roller.coasters.presentation.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

@Composable
public operator fun PaddingValues.plus(
    extraPadding: PaddingValues,
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = calculateTopPadding() + extraPadding.calculateTopPadding(),
        bottom = calculateBottomPadding() + extraPadding.calculateBottomPadding(),
        start = calculateStartPadding(layoutDirection) +
                extraPadding.calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection) +
                extraPadding.calculateEndPadding(layoutDirection),
    )
}

public fun PaddingValues.override(
    top: Dp? = null,
    bottom: Dp? = null,
    start: Dp? = null,
    end: Dp? = null,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
): PaddingValues = PaddingValues(
    top = top ?: this.calculateTopPadding(),
    bottom = bottom ?: this.calculateBottomPadding(),
    start = start ?: this.calculateStartPadding(layoutDirection),
    end = end ?: this.calculateEndPadding(layoutDirection),
)
