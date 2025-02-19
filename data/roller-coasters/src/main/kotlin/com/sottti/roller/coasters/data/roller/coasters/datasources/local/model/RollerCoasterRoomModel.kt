package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
@Entity(
    tableName = "roller_coasters",
    indices = [Index(value = ["id"], unique = true)],
)
internal data class RollerCoasterRoomModel(
    @Embedded val location: LocationRoomModel,
    @Embedded val mainPicture: PictureRoomModel?,
    @Embedded val name: NameRoomModel,
    @Embedded val park: ParkRoomModel,
    @Embedded val specs: SpecsRoomModel,
    @Embedded val status: StatusRoomModel,
    @PrimaryKey val id: Int,
)