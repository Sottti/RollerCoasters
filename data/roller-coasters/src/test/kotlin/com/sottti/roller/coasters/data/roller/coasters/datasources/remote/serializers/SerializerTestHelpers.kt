package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject

internal val json = Json { ignoreUnknownKeys = true }

internal val emptyJsonObject = buildJsonObject {}