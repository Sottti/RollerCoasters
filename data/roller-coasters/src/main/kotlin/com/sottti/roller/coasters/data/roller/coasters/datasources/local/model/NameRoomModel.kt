package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class NameRoomModel(
    @ColumnInfo(name = "name_current") val current: String,
    @ColumnInfo(name = "name_former") val former: String?,
)