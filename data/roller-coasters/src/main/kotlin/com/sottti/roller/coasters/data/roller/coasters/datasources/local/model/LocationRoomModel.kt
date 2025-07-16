package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
internal data class LocationRoomModel(
    @Embedded val coordinates: CoordinatesRoomModel?,
    val city: String,
    val country: String,
    val relocations: String?,
)