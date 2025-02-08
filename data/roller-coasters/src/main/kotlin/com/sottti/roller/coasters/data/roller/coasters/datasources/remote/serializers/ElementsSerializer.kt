package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

@OptIn(ExperimentalSerializationApi::class)
internal object ElementsSerializer : KSerializer<List<String>> {

    override val descriptor: SerialDescriptor =
        ListSerializer(String.serializer()).descriptor

    override fun serialize(
        encoder: Encoder,
        value: List<String>
    ) {
        encoder.encodeSerializableValue(ListSerializer(String.serializer()), value)
    }

    override fun deserialize(decoder: Decoder): List<String> =
        when (val element = decoder.jsonElement()) {
            is JsonArray -> element.map { it.jsonPrimitive.content.trim() }
            is JsonPrimitive -> listOf(element.content.trim())
            else -> emptyList()
        }
}