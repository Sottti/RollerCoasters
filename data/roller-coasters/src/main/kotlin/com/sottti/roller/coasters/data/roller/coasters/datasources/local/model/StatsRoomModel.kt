package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class RideRoomModel(
    val drop: List<Double>?,
    val duration: List<Int>?,
    val gForce: List<Double>?,
    val height: List<Double>?,
    val inversions: List<Int>?,
    val length: List<Double>?,
    val maxHeight: Double?,
    val maxVertical: List<Int>?,
    val speed: List<Double>?,
    val trackNames: List<String>?,
)