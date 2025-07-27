package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.TrimmingStringSerializer
import kotlinx.serialization.Serializable

@Serializable
internal data class StringWrapperApiModel(
    @Serializable(with = TrimmingStringSerializer::class)
    val value: String,
)
