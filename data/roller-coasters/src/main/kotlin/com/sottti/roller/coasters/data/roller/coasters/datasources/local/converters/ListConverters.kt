package com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters

import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoasterElement
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

internal fun List<RollerCoasterElement>?.toJson(): String? =
    this?.let { Json.encodeToString(stringListSerializer(), it.map { it.value }) }

internal fun String?.toElementsList(): List<RollerCoasterElement>? =
    this?.let { Json.decodeFromString(stringListSerializer(), it).map { RollerCoasterElement(it) } }

private fun stringListSerializer(): KSerializer<List<String>> = ListSerializer(String.serializer())