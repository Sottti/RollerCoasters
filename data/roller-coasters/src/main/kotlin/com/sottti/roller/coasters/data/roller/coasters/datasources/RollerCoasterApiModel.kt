@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package com.sottti.roller.coasters.data.roller.coasters.datasources

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RollerCoasterApiModel(
    val id: Int,
    val name: String,
    val park: ParkApiModel,
    val city: String,
    val state: String,
    val region: String,
    val country: String,
    val link: String,
    val make: String,
    val model: String,
    val type: String,
    val design: String,
    val status: StatusApiModel,
    val stats: StatsApiModel? = null,
    val mainPicture: PictureApiModel? = null,
    val pictures: List<PictureApiModel> = emptyList(),
    @SerialName("coords") val coordinates: CoordinatesApiModel
)

@Serializable
internal data class ParkApiModel(
    val id: Int,
    val name: String
)

@Serializable
internal data class StatusApiModel(
    val state: String,
    val date: StatusDateApiModel
)

@Serializable
internal data class StatusDateApiModel(
    val opened: String,
    val closed: String? = null,
)

@Serializable
internal data class StatsApiModel(
    val length: String? = null,
    val height: String? = null,
    val speed: String? = null,
    val inversions: String? = null,
    val duration: String? = null,
    val arrangement: String? = null,
    val capacity: String? = null,
    val dimensions: DimensionsApiModel? = null,
    val designer: String? = null,
    val verticalAngle: String? = null,
    val gForce: String? = null,
    val drop: String? = null,
    val cost: String? = null,
    val builtBy: String? = null,
    val elements: ElementsApiModel? = null,
    val formerNames: String? = null
)

@Serializable
internal data class DimensionsApiModel(
    @SerialName("dimensions")
    val values: List<String>? = null,
)

@Serializable
internal data class ElementsApiModel(
    @SerialName("elements")
    val values: List<String>? = null,
)

@Serializable
internal data class PictureApiModel(
    val url: String? = null,
)

@Serializable
internal data class CoordinatesApiModel(
    val lat: String? = null,
    val lng: String? = null,
)