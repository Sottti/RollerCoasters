package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

@Serializable
internal data class StringWrapperApiModel(
    @Serializable(with = TrimmingStringSerializer::class)
    val value: String
)