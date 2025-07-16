package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.Serializable

@Serializable
internal data class StatusRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_OPENED_DATE) val openedDate: String?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_STATUS_CURRENT) val current: String?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_STATUS_FORMER) val former: String?,
    val closedDate: String?,
)