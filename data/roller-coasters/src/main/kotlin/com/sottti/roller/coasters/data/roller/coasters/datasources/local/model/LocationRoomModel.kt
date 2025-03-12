package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class LocationRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_LOCATION_STATE) val state: String,
    @Embedded val coordinates: CoordinatesRoomModel?,
    val city: String,
    val country: String,
    val region: String,
    val relocations: String?,
)