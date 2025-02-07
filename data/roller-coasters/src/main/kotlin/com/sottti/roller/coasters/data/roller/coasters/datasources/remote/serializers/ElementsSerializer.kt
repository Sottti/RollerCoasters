package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PictureApiModel::class)
internal object ElementsSerializer : KSerializer<List<String>> {

    override val descriptor: SerialDescriptor =
        ListSerializer(String.serializer()).descriptor

    override fun deserialize(decoder: Decoder): List<String> =
        when (val element = decoder.jsonElement()) {
            is JsonArray -> element.map { it.jsonPrimitive.content.trim() }
            is JsonPrimitive -> listOf(element.content.trim())
            else -> emptyList()
        }
}