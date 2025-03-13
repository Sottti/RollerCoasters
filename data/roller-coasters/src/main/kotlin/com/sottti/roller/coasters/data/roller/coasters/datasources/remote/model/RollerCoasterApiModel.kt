@file:OptIn(InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.CoordinatesSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.DoubleOrListSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.IntOrListSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.PictureSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.StringOrListSerializer
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers.TrimmingStringSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RollerCoasterApiModel(
    @SerialName("city")
    @Serializable(with = TrimmingStringSerializer::class)
    val city: String,
    @SerialName("coords")
    @Serializable(with = CoordinatesSerializer::class)
    val coords: CoordinatesApiModel?,
    @SerialName("country")
    @Serializable(with = TrimmingStringSerializer::class)
    val country: String,
    @SerialName("design")
    @Serializable(with = TrimmingStringSerializer::class)
    val design: String,
    @SerialName("id")
    val id: Int,
    @SerialName("link")
    @Serializable(with = TrimmingStringSerializer::class)
    val link: String,
    @SerialName("mainPicture")
    @Serializable(with = PictureSerializer::class)
    val mainPicture: PictureApiModel?,
    @SerialName("make")
    @Serializable(with = TrimmingStringSerializer::class)
    val make: String,
    @SerialName("model")
    @Serializable(with = TrimmingStringSerializer::class)
    val model: String,
    @SerialName("name")
    @Serializable(with = TrimmingStringSerializer::class)
    val name: String,
    @SerialName("park")
    val park: AmusementParkApiModel,
    @SerialName("pictures")
    val pictures: List<PictureApiModel>,
    @SerialName("region")
    @Serializable(with = TrimmingStringSerializer::class)
    val region: String,
    @SerialName("state")
    @Serializable(with = TrimmingStringSerializer::class)
    val state: String,
    @SerialName("stats")
    val stats: RollerCoasterStatsApiModel?,
    @SerialName("status")
    val status: RollerCoasterStatusApiModel,
    @SerialName("type")
    @Serializable(with = TrimmingStringSerializer::class)
    val type: String,
)

@Serializable
internal data class AmusementParkApiModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    @Serializable(with = TrimmingStringSerializer::class)
    val name: String,
)

@Serializable
internal data class RollerCoasterStatusApiModel(
    @SerialName("date")
    val date: RollerCoasterStatusDateApiModel,
    @SerialName("state")
    @Serializable(with = TrimmingStringSerializer::class)
    val state: String,
)

@Serializable
internal data class RollerCoasterStatusDateApiModel(
    @SerialName("closed")
    @Serializable(with = TrimmingStringSerializer::class)
    val closed: String?,
    @SerialName("opened")
    @Serializable(with = TrimmingStringSerializer::class)
    val opened: String,
)

@Serializable
internal data class RollerCoasterStatsApiModel(
    @SerialName("arrangement")
    @Serializable(with = TrimmingStringSerializer::class)
    val arrangement: String?,
    @SerialName("builtBy")
    @Serializable(with = TrimmingStringSerializer::class)
    val builtBy: String?,
    @SerialName("capacity")
    val capacity: Int?,
    @SerialName("cost")
    val cost: Long?,
    @SerialName("designer")
    @Serializable(with = TrimmingStringSerializer::class)
    val designer: String?,
    @SerialName("dimensions")
    val dimensions: Double?,
    @SerialName("drop")
    @Serializable(with = DoubleOrListSerializer::class)
    val drop: List<Double>?,
    @SerialName("duration")
    @Serializable(with = StringOrListSerializer::class)
    val duration: List<String>?,
    @SerialName("formerNames")
    @Serializable(with = TrimmingStringSerializer::class)
    val formerNames: String?,
    @SerialName("formerStatus")
    @Serializable(with = TrimmingStringSerializer::class)
    val formerStatus: String?,
    @SerialName("restraints")
    @Serializable(with = TrimmingStringSerializer::class)
    val restraints: String?,
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
    @SerialName("relocations")
    @Serializable(with = TrimmingStringSerializer::class)
    val relocations: String?,
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
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    @Serializable(with = TrimmingStringSerializer::class)
    val name: String,
    @SerialName("url")
    @Serializable(with = TrimmingStringSerializer::class)
    val url: String,
    @SerialName("copyName")
    @Serializable(with = TrimmingStringSerializer::class)
    val copyName: String,
    @SerialName("copyDate")
    @Serializable(with = TrimmingStringSerializer::class)
    val copyDate: String,
)

@Serializable
internal data class CoordinatesApiModel(
    @SerialName("lat") val lat: Double,
    @SerialName("lng") val lng: Double,
)