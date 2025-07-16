package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
internal data class SpecsRoomModel(
    @Embedded val design: DesignRoomModel,
    @Embedded val ride: RideRoomModel?,
    val capacity: Int?,
    val cost: Long?,
    val dimensions: Double?,
    val manufacturer: String?,
    val model: String,
)