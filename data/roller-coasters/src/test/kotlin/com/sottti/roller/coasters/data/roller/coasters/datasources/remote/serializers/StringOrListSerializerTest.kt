package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

internal class StringOrListSerializerTest {

    @Test
    fun `deserialize null returns null`() {
        val result = decode(JsonNull)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize single string as list`() {
        val jsonElement = JsonPrimitive("Hello")
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf("Hello"))
    }

    @Test
    fun `deserialize list of strings`() {
        val jsonElement = JsonArray(
            listOf(JsonPrimitive("One"), JsonPrimitive("Two"), JsonPrimitive("Three")),
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf("One", "Two", "Three"))
    }

    @Test
    fun `deserialize empty list returns null`() {
        val jsonElement = JsonArray(emptyList())
        val result = decode(jsonElement)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize invalid single value - empty string returns null`() {
        val jsonElement = JsonPrimitive("")
        val result = decode(jsonElement)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize list with invalid values filters them out`() {
        val jsonElement = JsonArray(
            listOf(
                JsonPrimitive("Hello"),
                JsonPrimitive(""),
                JsonPrimitive("World"),
            ),
        )
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(listOf("Hello", "World"))
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
        val result = encode(listOf("Hello"))

        assertThat(result).isEqualTo(JsonArray(listOf(JsonPrimitive("Hello"))))
    }

    @Test
    fun `serialize multiple values encodes correctly`() {
        val result = encode(listOf("One", "Two", "Three"))

        assertThat(result).isEqualTo(
            JsonArray(
                listOf(
                    JsonPrimitive("One"),
                    JsonPrimitive("Two"),
                    JsonPrimitive("Three"),
                ),
            ),
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

private fun decode(jsonElement: JsonElement): List<String>? =
    json.decodeFromJsonElement(StringOrListSerializer, jsonElement)

private fun encode(value: List<String>?): JsonElement =
    json.encodeToJsonElement(StringOrListSerializer, value)