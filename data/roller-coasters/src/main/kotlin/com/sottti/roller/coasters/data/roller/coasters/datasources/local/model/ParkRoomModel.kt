package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class ParkRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_AMUSEMENT_PARK_ID) val id: Int,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_AMUSEMENT_PARK_NAME) val name: String,
)