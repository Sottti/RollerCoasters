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
    @PrimaryKey val id: Int,
    val name: String,
    @Embedded val park: AmusementParkRoomModel,
    @Embedded val location: LocationRoomModel,
    @Embedded val status: StatusRoomModel,
    val manufacturer: String,
    val model: String,
    val type: String,
    val design: String,
    @Embedded val stats: StatsRoomModel?,
    @Embedded val mainPicture: PictureRoomModel?
)