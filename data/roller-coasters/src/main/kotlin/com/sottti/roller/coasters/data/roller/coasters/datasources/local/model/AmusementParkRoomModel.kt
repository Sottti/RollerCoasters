package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class AmusementParkRoomModel(
    @ColumnInfo(name = "amusement_park_id") val id: Int,
    @ColumnInfo(name = "amusement_park_name") val name: String,
)