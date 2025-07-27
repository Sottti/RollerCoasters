package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.Serializable

@Serializable
internal data class RideRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_DROP_MAX) val dropMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_G_FORCE_MAX) val gForceMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_HEIGHT_MAX) val heightMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_INVERSIONS_MAX) val inversionsMax: Int?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_LENGTH_MAX) val lengthMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_MAX_VERTICAL_MAX) val maxVerticalMax: Int?,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_SPEED_MAX) val speedMax: Double?,
    val drop: List<Double>?,
    val duration: List<Int>?,
    val gForce: List<Double>?,
    val height: List<Double>?,
    val inversions: List<Int>?,
    val length: List<Double>?,
    val maxVertical: List<Int>?,
    val speed: List<Double>?,
    val trackNames: List<String>?,
)
