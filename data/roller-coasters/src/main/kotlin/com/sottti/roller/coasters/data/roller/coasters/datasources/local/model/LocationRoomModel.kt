package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Embedded
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class LocationRoomModel(
    val city: String,
    @Embedded val coordinates: CoordinatesRoomModel?,
    val country: String,
    val region: String,
    val state: String,
)