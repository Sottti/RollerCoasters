package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CoordinatesRoomModel(
    val latitude: Double,
    val longitude: Double,
)