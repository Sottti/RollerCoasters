package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@InternalSerializationApi
@Entity(tableName = RollerCoasterRoomModelConstants.TABLE_PICTURES)
internal data class PictureRoomModel(
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_PICTURE_ID) @PrimaryKey val id: Int,
    @ColumnInfo(name = RollerCoasterRoomModelConstants.COL_PICTURE_NAME) val name: String,
    val copyrightDate: String?,
    val copyrightName: String,
    val rollerCoasterId: Int,
    val url: String,
)