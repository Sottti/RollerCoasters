package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class NameRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_NAME_CURRENT) val current: String,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_NAME_FORMER) val former: String?,
)