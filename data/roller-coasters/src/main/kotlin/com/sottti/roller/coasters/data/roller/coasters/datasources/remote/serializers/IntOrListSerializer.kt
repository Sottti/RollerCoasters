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
import kotlinx.serialization.json.jsonPrimitive

internal object IntOrListSerializer : KSerializer<List<Int>?> {

    override val descriptor: SerialDescriptor =
        ListSerializer(Int.serializer()).descriptor

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: List<Int>?) =
        when {
            value.isNullOrEmpty() -> encoder.encodeNull()
            else -> encoder.encodeSerializableValue(ListSerializer(Int.serializer()), value)
        }

    override fun deserialize(decoder: Decoder): List<Int>? =
        when (val element = decoder.jsonElement()) {
            is JsonNull -> null
            is JsonPrimitive -> element.content.toIntOrNull()?.let { listOf(it) }
            is JsonArray -> element.mapNotNull {
                it.jsonPrimitive.content.toIntOrNull()
            }.ifEmpty { null }

            else -> null
        }
}