package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.API_FIELD_LATITUDE
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.API_FIELD_LONGITUDE
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.coordinatesApiModel
import com.sottti.roller.coasters.utils.test.stubs.LATITUDE
import com.sottti.roller.coasters.utils.test.stubs.LONGITUDE
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

internal class CoordinatesSerializerTest {

    @Test
    fun `deserialize valid coordinates JSON`() {
        val coordinates = decodeCoordinatesJson(LATITUDE, LONGITUDE)

        assertThat(coordinates).isNotNull()
        assertThat(coordinates).isEqualTo(coordinatesApiModel)
    }

    @Test
    fun `deserialize invalid coordinates JSON - missing fields`() {
        val coordinates = decodeCoordinatesJson(LATITUDE, null)

        assertThat(coordinates).isNull()
    }

    @Test
    fun `deserialize invalid coordinates JSON - empty values`() {
        val coordinates = decodeCoordinatesJson("", "")

        assertThat(coordinates).isNull()
    }

    @Test
    fun `deserialize invalid coordinates JSON - non-numeric values`() {
        val coordinates = decodeCoordinatesJson("invalid", "data")

        assertThat(coordinates).isNull()
    }

    @Test
    fun `deserialize empty JSON object`() {
        val coordinates = decode(emptyJsonObject)

        assertThat(coordinates).isNull()
    }
}

private fun createCoordinatesJson(latitude: Double?, longitude: Double?): JsonObject =
    createCoordinatesJson(latitude.toString(), longitude.toString())

@Suppress("SameParameterValue")
private fun decodeCoordinatesJson(latitude: Double?, longitude: Double?) =
    decode(createCoordinatesJson(latitude = latitude, longitude = longitude))

private fun decodeCoordinatesJson(latitude: String?, longitude: String?) =
    decode(createCoordinatesJson(latitude = latitude, longitude = longitude))

private fun decode(jsonElement: JsonObject) =
    json.decodeFromJsonElement(CoordinatesSerializer, jsonElement)

private fun createCoordinatesJson(latitude: String?, longitude: String?): JsonObject =
    buildJsonObject {
        latitude?.let { latitude -> put(API_FIELD_LATITUDE, latitude) }
        longitude?.let { longitude -> put(API_FIELD_LONGITUDE, longitude) }
    }