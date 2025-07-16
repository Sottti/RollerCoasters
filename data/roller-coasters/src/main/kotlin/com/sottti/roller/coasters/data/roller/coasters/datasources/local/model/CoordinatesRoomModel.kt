package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@OptIn(InternalSerializationApi::class)
internal data class CoordinatesRoomModel(
    val latitude: Double,
    val longitude: Double,
)