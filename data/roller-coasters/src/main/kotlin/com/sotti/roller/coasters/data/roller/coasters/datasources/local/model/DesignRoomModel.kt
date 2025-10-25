package com.sotti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import com.sotti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.Serializable

@Serializable
internal data class DesignRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_TYPE) val type: String,
    val arrangement: String?,
    val designer: String?,
    val elements: String?,
    val restraints: String?,
    val train: String,
)
