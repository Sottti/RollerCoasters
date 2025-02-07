package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonDecoder

internal fun Decoder.jsonElement() =
    asJsonDecoder().decodeJsonElement()

private fun Decoder.asJsonDecoder() = this as? JsonDecoder
    ?: throw IllegalStateException("Expected JsonDecoder but found ${this::class}")