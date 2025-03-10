package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class RideRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_DROP_MAX) val dropMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_G_FORCE_MAX) val gForceMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_HEIGHT_MAX) val heightMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_INVERSIONS_MAX) val inversionsMax: Int?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_LENGTH_MAX) val lengthMax: Double?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_MAX_VERTICAL_MAX) val maxVerticalMax: Int?,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_SPEED_MAX) val speedMax: Double?,
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