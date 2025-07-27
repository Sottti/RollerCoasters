package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
internal data class FavouriteRollerCoasterRoomModel(
    @PrimaryKey val rollerCoasterId: Int,
)
