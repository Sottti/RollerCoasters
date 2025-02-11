package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
@Entity(tableName = "roller_coasters")
internal data class RollerCoasterRoomModel(
    @Embedded val location: LocationRoomModel,
    @Embedded val mainPicture: PictureRoomModel?,
    @Embedded val park: AmusementParkRoomModel,
    @Embedded val stats: StatsRoomModel?,
    @Embedded val status: StatusRoomModel,
    @PrimaryKey val id: Int,
    val design: String,
    val manufacturer: String?,
    val model: String,
    val name: String,
    val type: String,
)