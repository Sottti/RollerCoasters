package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.StringWrapperApiModel
import kotlinx.serialization.json.Json
import org.junit.Test

internal class TrimmingStringSerializerTest {

    @Test
    fun `deserialize removes leading and trailing spaces`() {
        val json = """{"value": "   hello world   "}"""
        val wrapper = Json.decodeFromString<StringWrapperApiModel>(json)

        assertThat(wrapper.value).isEqualTo("hello world")
    }

    @Test
    fun `deserialize works with empty string`() {
        val json = """{"value": "   "}"""
        val wrapper = Json.decodeFromString<StringWrapperApiModel>(json)

        assertThat(wrapper.value).isEmpty()
    }

    @Test
    fun `deserialize leaves inner spaces intact`() {
        val json = """{"value": "  hello   world  "}"""
        val wrapper = Json.decodeFromString<StringWrapperApiModel>(json)

        assertThat(wrapper.value).isEqualTo("hello   world")
    }

    @Test
    fun `serialize does not trim`() {
        val wrapper = StringWrapperApiModel("   spaced   out   ")
        val json = Json.encodeToString(wrapper)

        assertThat(json).contains("   spaced   out   ")
    }
}