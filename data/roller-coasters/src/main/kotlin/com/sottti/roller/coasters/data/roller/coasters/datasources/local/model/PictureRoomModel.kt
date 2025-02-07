package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
@Entity(tableName = "pictures")
internal data class PictureRoomModel(
    @PrimaryKey val id: Int,
    val copyrightDate: String,
    val copyrightName: String,
    val name: String,
    val rollerCoasterId: Int,
    val url: String,
)