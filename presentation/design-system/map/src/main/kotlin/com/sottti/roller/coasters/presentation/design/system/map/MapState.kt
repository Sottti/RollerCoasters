package com.sottti.roller.coasters.presentation.design.system.map

import androidx.compose.ui.Modifier

internal data class MapState(
    val latitude: Double,
    val longitude: Double,
    val markerTitle: String,
    val modifier: Modifier = Modifier,
)