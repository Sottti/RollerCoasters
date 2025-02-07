@file:OptIn(InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.CoordinatesSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.ElementsSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.PictureSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
internal data class RollerCoasterApiModel(
    val city: String,
    @Serializable(with = CoordinatesSerializer::class)
    val coords: CoordinatesApiModel?,
    val country: String,
    val design: String,
    val id: Int,
    val link: String,
    @Serializable(with = PictureSerializer::class)
    val mainPicture: PictureApiModel?,
    val make: String,
    val manufactured: Int?,
    val model: String,
    val name: String,
    val park: AmusementParkApiModel,
    val pictures: List<PictureApiModel>,
    val region: String,
    val state: String,
    val stats: RollerCoasterStatsApiModel?,
    val status: RollerCoasterStatusApiModel,
    val type: String,
)

@Serializable
internal data class AmusementParkApiModel(
    val id: Int,
    val name: String
)

@Serializable
internal data class RollerCoasterStatusApiModel(
    val date: RollerCoasterStatusDateApiModel,
    val state: String,
)

@Serializable
internal data class RollerCoasterStatusDateApiModel(
    val closed: String?,
    val opened: String,
)

@Serializable
internal data class RollerCoasterStatsApiModel(
    val arrangement: String?,
    val builtBy: String?,
    val capacity: Int?,
    val cost: Int?,
    val designer: String?,
    val dimensions: Double?,
    val drop: Double?,
    val duration: Int?,
    @Serializable(with = ElementsSerializer::class)
    val elements: List<String>?,
    val formerNames: String?,
    val formerStatus: String?,
    val gForce: Double?,
    val height: Double?,
    val inversions: Int?,
    val length: Double?,
    val relocations: String?,
    val speed: Double?,
    val verticalAngle: Int?,
)

@Serializable
internal data class PictureApiModel(
    val id: Int,
    val name: String,
    val url: String,
    val copyName: String,
    val copyDate: String,
)

@Serializable
internal data class CoordinatesApiModel(
    val lat: Double,
    val lng: Double,
)