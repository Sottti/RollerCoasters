package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.PictureApiModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.nullable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@VisibleForTesting
internal const val UNEXPECTED_PICTURE_FORMAT_MESSAGE = "Unexpected format for mainPicture: %s"

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PictureApiModel::class)
internal object PictureSerializer : KSerializer<PictureApiModel?> {

    override val descriptor = PictureApiModel.serializer().descriptor.nullable

    override fun deserialize(decoder: Decoder): PictureApiModel? =
        when (val element = decoder.jsonElement()) {
            is JsonNull -> null
            is JsonPrimitive if (element.content.isUndefined()) -> null
            is JsonObject -> {
                if (element.isEmpty()) null
                else Json.decodeFromJsonElement(PictureApiModel.serializer(), element)
            }

            else -> throw IllegalArgumentException("Unexpected format for mainPicture: $element")
        }
}
