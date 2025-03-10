package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class RideRoomModel(
    val drop: List<Double>?,
    val dropMax: Double?,
    val duration: List<Int>?,
    val gForce: List<Double>?,
    val gForceMax: Double?,
    val height: List<Double>?,
    val heightMax: Double?,
    val inversions: List<Int>?,
    val inversionsMax: Int?,
    val length: List<Double>?,
    val lengthMax: Double?,
    val maxVertical: List<Int>?,
    val maxVerticalMax: Int?,
    val speed: List<Double>?,
    val speedMax: Double?,
    val trackNames: List<String>?,
)