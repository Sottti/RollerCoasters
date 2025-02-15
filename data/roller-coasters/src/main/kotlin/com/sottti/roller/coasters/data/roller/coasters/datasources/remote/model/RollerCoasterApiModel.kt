@file:OptIn(InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.CoordinatesSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.DoubleOrListSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.IntOrListSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.PictureSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.StringOrListSerializer
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
    @SerialName("cost") val cost: Long?,
    @SerialName("designer") val designer: String?,
    @SerialName("dimensions") val dimensions: Double?,
    @SerialName("drop")
    @Serializable(with = DoubleOrListSerializer::class)
    val drop: List<Double>?,
    @SerialName("duration")
    @Serializable(with = StringOrListSerializer::class)
    val duration: List<String>?,
    @SerialName("formerNames") val formerNames: String?,
    @SerialName("formerStatus") val formerStatus: String?,
    @SerialName("restraints") val restraints: String?,
    @SerialName("gForce")
    @Serializable(with = DoubleOrListSerializer::class)
    val gForce: List<Double>?,
    @SerialName("height")
    @Serializable(with = DoubleOrListSerializer::class)
    val height: List<Double>?,
    @SerialName("inversions")
    @Serializable(with = IntOrListSerializer::class)
    val inversions: List<Int>?,
    @SerialName("length")
    @Serializable(with = DoubleOrListSerializer::class)
    val length: List<Double>?,
    @SerialName("name")
    @Serializable(with = StringOrListSerializer::class)
    val name: List<String>?,
    @SerialName("relocations") val relocations: String?,
    @SerialName("speed")
    @Serializable(with = DoubleOrListSerializer::class)
    val speed: List<Double>?,
    @SerialName("verticalAngle")
    @Serializable(with = IntOrListSerializer::class)
    val verticalAngle: List<Int>?,
    @SerialName("elements")
    @Serializable(with = StringOrListSerializer::class)
    val elements: List<String>?,
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