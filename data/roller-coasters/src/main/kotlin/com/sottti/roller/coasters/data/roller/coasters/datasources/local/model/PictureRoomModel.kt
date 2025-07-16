package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@OptIn(InternalSerializationApi::class)
@Entity(tableName = RollerCoasterRoomConstants.TABLE_PICTURES)
internal data class PictureRoomModel(
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_PICTURE_ID) @PrimaryKey val id: Int,
    @ColumnInfo(name = RollerCoasterRoomConstants.COL_PICTURE_NAME) val name: String,
    val copyrightDate: String?,
    val copyrightName: String,
    val rollerCoasterId: Int,
    val url: String,
)