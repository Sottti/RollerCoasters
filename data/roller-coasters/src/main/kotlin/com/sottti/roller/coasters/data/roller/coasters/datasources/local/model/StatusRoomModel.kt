package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
internal data class StatusRoomModel(
    @ColumnInfo(name = "status_state") val state: String,
    val closedDate: String,
    val openedDate: String,
)