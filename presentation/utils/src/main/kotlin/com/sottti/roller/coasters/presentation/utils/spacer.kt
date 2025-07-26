package com.sottti.roller.coasters.presentation.utils

import androidx.compose.foundation.layout.Spacer as ComposeSpacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
public fun Spacer(size: Dp) {
    ComposeSpacer(modifier = Modifier.size(size))
}
