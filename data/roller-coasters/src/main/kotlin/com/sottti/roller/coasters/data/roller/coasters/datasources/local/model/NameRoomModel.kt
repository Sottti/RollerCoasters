package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.Serializable

@Serializable
internal data class NameRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_NAME_CURRENT) val current: String,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_NAME_FORMER) val former: String?,
)