package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.API_FIELD_LATITUDE
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.API_FIELD_LONGITUDE
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.CoordinatesApiModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = CoordinatesApiModel::class)
internal object CoordinatesSerializer : KSerializer<CoordinatesApiModel?> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(CoordinatesApiModel::class.java.simpleName) {
            element<Double>(API_FIELD_LATITUDE)
            element<Double>(API_FIELD_LONGITUDE)
        }

    override fun deserialize(decoder: Decoder): CoordinatesApiModel? {
        val element = decoder.jsonElement()

        if (element is JsonObject) {
            val latStr = element[API_FIELD_LATITUDE]?.jsonPrimitive?.contentOrNull
            val lngStr = element[API_FIELD_LONGITUDE]?.jsonPrimitive?.contentOrNull

            if (latStr.isUndefined() || lngStr.isUndefined()) return null

            return latStr?.toDoubleOrNull()?.let { lat ->
                lngStr?.toDoubleOrNull()?.let { lng ->
                    CoordinatesApiModel(lat, lng)
                }
            }
        }
        return null
    }
}