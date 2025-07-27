package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

internal class IntOrListSerializerTest {

    @Test
    fun `deserialize null returns null`() {
        val result = decode(JsonNull)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize single int as list`() {
        val jsonElement = JsonPrimitive("42")
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(42))
    }

    @Test
    fun `deserialize list of ints`() {
        val jsonElement = JsonArray(
            listOf(JsonPrimitive(1), JsonPrimitive(2), JsonPrimitive(3)),
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(1, 2, 3))
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
                JsonPrimitive("12"),
                JsonPrimitive("invalid"),
                JsonPrimitive("45"),
            )
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf(12, 45))
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
        val result = encode(listOf(42))

        assertThat(result).isEqualTo(JsonArray(listOf(JsonPrimitive(42))))
    }

    @Test
    fun `serialize multiple values encodes correctly`() {
        val result = encode(listOf(1, 2, 3))

        assertThat(result).isEqualTo(
            JsonArray(
                listOf(
                    JsonPrimitive(1),
                    JsonPrimitive(2),
                    JsonPrimitive(3)
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

private fun decode(jsonElement: JsonElement): List<Int>? =
    json.decodeFromJsonElement(IntOrListSerializer, jsonElement)

private fun encode(value: List<Int>?): JsonElement =
    json.encodeToJsonElement(IntOrListSerializer, value)
