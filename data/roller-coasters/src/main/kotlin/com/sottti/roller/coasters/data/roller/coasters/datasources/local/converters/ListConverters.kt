package com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

internal object ListConverters {

    @TypeConverter
    fun fromStringList(value: List<String>?): String? =
        value?.let { Json.encodeToString(ListSerializer(String.serializer()), it) }

    @TypeConverter
    fun toStringList(value: String?): List<String>? =
        value?.let { Json.decodeFromString(ListSerializer(String.serializer()), it) }

    @TypeConverter
    fun fromDoubleList(value: List<Double>?): String? =
        value?.let { Json.encodeToString(ListSerializer(Double.serializer()), it) }

    @TypeConverter
    fun toDoubleList(value: String?): List<Double>? =
        value?.let { Json.decodeFromString(ListSerializer(Double.serializer()), it) }

    @TypeConverter
    fun fromIntList(value: List<Int>?): String? =
        value?.let { Json.encodeToString(ListSerializer(Int.serializer()), it) }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? =
        value?.let { Json.decodeFromString(ListSerializer(Int.serializer()), it) }
}