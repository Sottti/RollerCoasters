package com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

internal class ListConvertersTest {

    private val converters = ListConverters

    private inline fun <reified T> testConversion(
        original: List<T>?,
        fromList: (List<T>?) -> String?,
        toList: (String?) -> List<T>?
    ) {
        val json = fromList(original)
        val result = toList(json)

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `Convert string list to JSON and back`() {
        testConversion(
            original = listOf("A", "B", "C"),
            fromList = converters::fromStringList,
            toList = converters::toStringList,
        )
    }

    @Test
    fun `Convert empty string list`() {
        testConversion(
            original = emptyList(),
            fromList = converters::fromStringList,
            toList = converters::toStringList,
        )
    }

    @Test
    fun `Convert null string list`() {
        testConversion(
            original = null,
            fromList = converters::fromStringList,
            toList = converters::toStringList,
        )
    }

    @Test
    fun `Convert double list to JSON and back`() {
        testConversion(
            original = listOf(1.1, 2.2, 3.3),
            fromList = converters::fromDoubleList,
            toList = converters::toDoubleList,
        )
    }

    @Test
    fun `Convert empty double list`() {
        testConversion(
            original = emptyList(),
            fromList = converters::fromDoubleList,
            toList = converters::toDoubleList,
        )
    }

    @Test
    fun `Convert null double list`() {
        testConversion(
            original = null,
            fromList = converters::fromDoubleList,
            toList = converters::toDoubleList,
        )
    }

    @Test
    fun `Convert int list to JSON and back`() {
        testConversion(
            original = listOf(1, 2, 3),
            fromList = converters::fromIntList,
            toList = converters::toIntList,
        )
    }

    @Test
    fun `Convert empty int list`() {
        testConversion(
            original = emptyList(),
            fromList = converters::fromIntList,
            toList = converters::toIntList,
        )
    }

    @Test
    fun `Convert null int list`() {
        testConversion(
            original = null,
            fromList = converters::fromIntList,
            toList = converters::toIntList,
        )
    }
}