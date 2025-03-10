package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class StatusRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_OPENED_DATE) val openedDate: String?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_STATUS_CURRENT) val current: String,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_STATUS_FORMER) val former: String?,
    val closedDate: String?,
)