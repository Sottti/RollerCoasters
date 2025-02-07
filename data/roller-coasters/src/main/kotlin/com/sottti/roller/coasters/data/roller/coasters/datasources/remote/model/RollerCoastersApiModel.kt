@file:OptIn(InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RollerCoastersApiModel(
    @SerialName("data") val data: List<RollerCoasterApiModel>,
    @SerialName("pagination") val pagination: PaginationApiModel,
)

@Serializable
internal data class PaginationApiModel(
    @SerialName("count") val count: Int,
    @SerialName("limit") val limit: Int,
    @SerialName("offset") val offset: Int,
    @SerialName("total") val total: Int,
)