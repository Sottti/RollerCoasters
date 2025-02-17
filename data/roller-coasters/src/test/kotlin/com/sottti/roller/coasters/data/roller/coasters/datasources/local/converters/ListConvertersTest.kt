package com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters

import com.google.common.truth.Truth
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.junit.Test

internal class ListConvertersTest {

    private val converters = ListConverters

    @Test
    fun `Convert string list to JSON and back`() {
        val original = listOf("A", "B", "C")
        val json = converters.fromStringList(original)
        val result = converters.toStringList(json)

        Truth.assertThat(json)
            .isEqualTo(
                Json.Default.encodeToString(
                    ListSerializer(String.Companion.serializer()),
                    original
                )
            )
        Truth.assertThat(result).isEqualTo(original)
    }

    @Test
    fun `Convert empty string list to JSON and back`() {
        val original = emptyList<String>()
        val json = converters.fromStringList(original)
        val result = converters.toStringList(json)

        Truth.assertThat(json)
            .isEqualTo(Json.Default.encodeToString(ListSerializer(String.serializer()), original))
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `Convert null string list should return null`() {
        Truth.assertThat(converters.fromStringList(null)).isNull()
        Truth.assertThat(converters.toStringList(null)).isNull()
    }

    @Test
    fun `Convert double list to JSON and back`() {
        val original = listOf(1.1, 2.2, 3.3)
        val json = converters.fromDoubleList(original)
        val result = converters.toDoubleList(json)

        Truth.assertThat(json)
            .isEqualTo(Json.Default.encodeToString(ListSerializer(Double.serializer()), original))
        Truth.assertThat(result).isEqualTo(original)
    }

    @Test
    fun `Convert empty double list to JSON and back`() {
        val original = emptyList<Double>()
        val json = converters.fromDoubleList(original)
        val result = converters.toDoubleList(json)

        Truth.assertThat(json)
            .isEqualTo(Json.Default.encodeToString(ListSerializer(Double.serializer()), original))
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `Convert null double list should return null`() {
        Truth.assertThat(converters.fromDoubleList(null)).isNull()
        Truth.assertThat(converters.toDoubleList(null)).isNull()
    }

    @Test
    fun `Convert int list to JSON and back`() {
        val original = listOf(1, 2, 3)
        val json = converters.fromIntList(original)
        val result = converters.toIntList(json)

        Truth.assertThat(json)
            .isEqualTo(Json.Default.encodeToString(ListSerializer(Int.serializer()), original))
        Truth.assertThat(result).isEqualTo(original)
    }

    @Test
    fun `Convert empty int list to JSON and back`() {
        val original = emptyList<Int>()
        val json = converters.fromIntList(original)
        val result = converters.toIntList(json)

        Truth.assertThat(json)
            .isEqualTo(Json.Default.encodeToString(ListSerializer(Int.serializer()), original))
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `Convert null int list should return null`() {
        Truth.assertThat(converters.fromIntList(null)).isNull()
        Truth.assertThat(converters.toIntList(null)).isNull()
    }
}