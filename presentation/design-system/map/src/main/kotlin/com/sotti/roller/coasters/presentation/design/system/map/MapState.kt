package com.sotti.roller.coasters.presentation.design.system.map

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
internal data class MapState(
    val latitude: Double,
    val longitude: Double,
    val markerTitle: String,
    val modifier: Modifier = Modifier,
)
