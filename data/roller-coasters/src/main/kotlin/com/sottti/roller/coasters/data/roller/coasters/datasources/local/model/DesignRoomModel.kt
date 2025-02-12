package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class DesignRoomModel(
    val arrangement: String?,
    val designer: String?,
    val elements: String?,
    val restraints: String?,
    val train: String,
    val type: String,
)