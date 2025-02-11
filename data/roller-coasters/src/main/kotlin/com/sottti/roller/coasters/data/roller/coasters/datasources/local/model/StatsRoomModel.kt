package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class StatsRoomModel(
    val arrangement: String?,
    val capacity: Int?,
    val cost: Long?,
    val designer: String?,
    val dimensions: Double?,
    val drop: List<Double>?,
    val duration: List<Int>?,
    val elements: String?,
    val formerNames: String?,
    val formerStatus: String?,
    val gForce: List<Double>?,
    val height: List<Double>?,
    val inversions: List<Int>?,
    val length: List<Double>?,
    val maxVertical: List<Int>?,
    val relocations: String?,
    val speed: List<Double>?,
    val trackNames: List<String>?,
)