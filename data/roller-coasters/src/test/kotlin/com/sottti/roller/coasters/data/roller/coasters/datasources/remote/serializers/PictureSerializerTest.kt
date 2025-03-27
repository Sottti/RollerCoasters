package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.mainPictureApiModel
import com.sottti.roller.coasters.utils.test.stubs.COASTER_NAME
import com.sottti.roller.coasters.utils.test.stubs.COPYRIGHT_DATE
import com.sottti.roller.coasters.utils.test.stubs.PICTURE_AUTHOR
import com.sottti.roller.coasters.utils.test.stubs.PICTURE_ID_MAIN
import com.sottti.roller.coasters.utils.test.stubs.PICTURE_URL
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test
import kotlin.test.assertFailsWith

internal class PictureSerializerTest {

    @Test
    fun `deserialize null JSON returns null`() {
        val result = decode(JsonNull)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize empty JSON object returns null`() {
        val result = decode(emptyJsonObject)

        assertThat(result).isNull()
    }

    @Test
    fun `deserialize valid picture JSON`() {
        val jsonElement = completePictureJson()
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(mainPictureApiModel)
    }

    @Test
    fun `deserialize picture JSON with undefined fields`() {
        val jsonElement = incompletePictureJson()
        val result = decode(jsonElement)

        assertThat(result).isEqualTo(mainPictureIncomplete)
    }

    @Test
    fun `deserialize picture JSON with invalid format throws exception`() {
        val jsonElement = JsonPrimitive("invalid_data")

        val exception = assertFailsWith<IllegalArgumentException> {
            decode(jsonElement)
        }

        assertThat(exception.message)
            .isEqualTo(UNEXPECTED_PICTURE_FORMAT_MESSAGE.format(jsonElement))
    }
}

private fun completePictureJson(): JsonObject =
    buildJsonObject {
        put("copyDate", COPYRIGHT_DATE)
        put("copyName", PICTURE_AUTHOR)
        put("id", PICTURE_ID_MAIN)
        put("name", COASTER_NAME)
        put("url", PICTURE_URL)
    }

private fun incompletePictureJson(): JsonObject =
    buildJsonObject {
        put("copyDate", "")
        put("copyName", PICTURE_AUTHOR)
        put("id", PICTURE_ID_MAIN)
        put("name", COASTER_NAME)
        put("url", PICTURE_URL)
    }

private val mainPictureIncomplete = mainPictureApiModel.copy(
    copyDate = "",
)

private fun decode(jsonElement: JsonElement): PictureApiModel? =
    json.decodeFromJsonElement(PictureSerializer, jsonElement)