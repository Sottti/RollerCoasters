package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class LocationRoomModel(
    @ColumnInfo(name = "location_state") val state: String,
    @Embedded val coordinates: CoordinatesRoomModel?,
    val city: String,
    val country: String,
    val region: String,
)