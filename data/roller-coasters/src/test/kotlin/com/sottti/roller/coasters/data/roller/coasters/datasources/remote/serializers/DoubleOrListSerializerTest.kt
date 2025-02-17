package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

internal class DoubleOrListSerializerTest {

    @Test
    fun `deserialize null returns null`() {
        val result = decode(JsonNull)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize single double as list`() {
        val jsonElement = JsonPrimitive("42.5")
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(42.5))
    }

    @Test
    fun `deserialize list of doubles`() {
        val jsonElement = JsonArray(
            listOf(JsonPrimitive(1.2), JsonPrimitive(3.4), JsonPrimitive(5.6)),
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(1.2, 3.4, 5.6))
    }

    @Test
    fun `deserialize empty list returns null`() {
        val jsonElement = JsonArray(emptyList())
        val result = decode(jsonElement)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize invalid single value returns null`() {
        val jsonElement = JsonPrimitive("invalid")
        val result = decode(jsonElement)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize list with invalid values filters them out`() {
        val jsonElement = JsonArray(
            listOf(
                JsonPrimitive("12.3"),
                JsonPrimitive("invalid"),
                JsonPrimitive("45.6")
            )
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(12.3, 45.6))
    }

    @Test
    fun `serialize null encodes to JsonNull`() {
        val result = encode(null)

        assertThat(result).isEqualTo(JsonNull)
    }

    @Test
    fun `serialize empty list encodes to JsonNull`() {
        val result = encode(emptyList())

        assertThat(result).isEqualTo(JsonNull)
    }

    @Test
    fun `serialize single value list encodes correctly`() {
        val result = encode(listOf(42.5))

        assertThat(result).isEqualTo(JsonArray(listOf(JsonPrimitive(42.5))))
    }

    @Test
    fun `serialize multiple values encodes correctly`() {
        val result = encode(listOf(1.2, 3.4, 5.6))

        assertThat(result).isEqualTo(
            JsonArray(
                listOf(
                    JsonPrimitive(1.2),
                    JsonPrimitive(3.4),
                    JsonPrimitive(5.6)
                )
            )
        )
    }

    @Test
    fun `deserialize invalid JSON structure does not throw exception`() {
        val jsonElement = buildJsonObject {
            put("key", "value")
        }

        val result = runCatching { decode(jsonElement) }

        assertThat(result.isFailure).isFalse()
    }
}

private fun decode(jsonElement: JsonElement): List<Double>? =
    json.decodeFromJsonElement(DoubleOrListSerializer, jsonElement)

private fun encode(value: List<Double>?): JsonElement =
    json.encodeToJsonElement(DoubleOrListSerializer, value)