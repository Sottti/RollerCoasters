@file:OptIn(InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.CoordinatesSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.ElementsSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.PictureSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RollerCoasterApiModel(
    @SerialName("city") val city: String,
    @SerialName("coords")
    @Serializable(with = CoordinatesSerializer::class)
    val coords: CoordinatesApiModel?,
    @SerialName("country") val country: String,
    @SerialName("design") val design: String,
    @SerialName("id") val id: Int,
    @SerialName("link") val link: String,
    @SerialName("mainPicture")
    @Serializable(with = PictureSerializer::class)
    val mainPicture: PictureApiModel?,
    @SerialName("make") val make: String,
    @SerialName("manufactured") val manufactured: Int?,
    @SerialName("model") val model: String,
    @SerialName("name") val name: String,
    @SerialName("park") val park: AmusementParkApiModel,
    @SerialName("pictures") val pictures: List<PictureApiModel>,
    @SerialName("region") val region: String,
    @SerialName("state") val state: String,
    @SerialName("stats") val stats: RollerCoasterStatsApiModel?,
    @SerialName("status") val status: RollerCoasterStatusApiModel,
    @SerialName("type") val type: String,
)

@Serializable
internal data class AmusementParkApiModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)

@Serializable
internal data class RollerCoasterStatusApiModel(
    @SerialName("date") val date: RollerCoasterStatusDateApiModel,
    @SerialName("state") val state: String,
)

@Serializable
internal data class RollerCoasterStatusDateApiModel(
    @SerialName("closed") val closed: String?,
    @SerialName("opened") val opened: String,
)

@Serializable
internal data class RollerCoasterStatsApiModel(
    @SerialName("arrangement") val arrangement: String?,
    @SerialName("builtBy") val builtBy: String?,
    @SerialName("capacity") val capacity: Int?,
    @SerialName("cost") val cost: Int?,
    @SerialName("designer") val designer: String?,
    @SerialName("dimensions") val dimensions: Double?,
    @SerialName("drop") val drop: Double?,
    @SerialName("duration") val duration: Int?,
    @SerialName("elements")
    @Serializable(with = ElementsSerializer::class)
    val elements: List<String>?,
    @SerialName("formerNames") val formerNames: String?,
    @SerialName("formerStatus") val formerStatus: String?,
    @SerialName("gForce") val gForce: Double?,
    @SerialName("height") val height: Double?,
    @SerialName("inversions") val inversions: Int?,
    @SerialName("length") val length: Double?,
    @SerialName("relocations") val relocations: String?,
    @SerialName("speed") val speed: Double?,
    @SerialName("verticalAngle") val verticalAngle: Int?,
)

@Serializable
internal data class PictureApiModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("copyName") val copyName: String,
    @SerialName("copyDate") val copyDate: String,
)

@Serializable
internal data class CoordinatesApiModel(
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
)