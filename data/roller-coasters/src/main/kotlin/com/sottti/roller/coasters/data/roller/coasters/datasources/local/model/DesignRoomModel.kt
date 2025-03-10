package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class DesignRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_TYPE) val type: String,
    val arrangement: String?,
    val designer: String?,
    val elements: String?,
    val restraints: String?,
    val train: String,
)