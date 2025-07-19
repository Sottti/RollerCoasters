package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchCoastersApiModel(
    @SerialName("coasters") val coasters: List<RollerCoasterApiModel>,
    @SerialName("totalMatch") val totalMatch: Int,
)
