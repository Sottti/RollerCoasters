package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.FavouriteRollerCoasterRoomModel
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId

internal fun RollerCoasterId.toFavouriteRollerCoasterRoomModel(): FavouriteRollerCoasterRoomModel =
    FavouriteRollerCoasterRoomModel(value)