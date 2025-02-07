package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class StatsRoomModel(
    val arrangement: String?,
    val capacity: Int?,
    val cost: Int?,
    val designer: String?,
    val dimensions: Double?,
    val drop: Double?,
    val duration: Int?,
    val elements: String?,
    val formerNames: String?,
    val formerStatus: String?,
    val gForce: Double?,
    val height: Double?,
    val inversions: Int?,
    val length: Double?,
    val relocations: String?,
    val speed: Double?,
)