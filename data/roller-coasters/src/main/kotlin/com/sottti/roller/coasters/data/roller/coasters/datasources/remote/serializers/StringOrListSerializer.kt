package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

internal object StringOrListSerializer : KSerializer<List<String>?> {

    override val descriptor: SerialDescriptor =
        ListSerializer(String.serializer()).descriptor

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: List<String>?) =
        when {
            value.isNullOrEmpty() -> encoder.encodeNull()
            else -> encoder.encodeSerializableValue(ListSerializer(String.serializer()), value)
        }

    override fun deserialize(decoder: Decoder): List<String>? =
        when (val element = decoder.jsonElement()) {
            is JsonNull -> null
            is JsonPrimitive -> element.content.takeIf { it.isNotBlank() }?.let { listOf(it) }
            is JsonArray -> element
                .mapNotNull { it.jsonPrimitive.contentOrNull?.takeIf { it.isNotBlank() } }
                .ifEmpty { null }

            else -> null
        }
}
