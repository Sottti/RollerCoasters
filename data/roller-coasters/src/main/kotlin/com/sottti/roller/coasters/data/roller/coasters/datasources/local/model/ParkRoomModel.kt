package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class ParkRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_AMUSEMENT_PARK_ID) val id: Int,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_AMUSEMENT_PARK_NAME) val name: String,
)