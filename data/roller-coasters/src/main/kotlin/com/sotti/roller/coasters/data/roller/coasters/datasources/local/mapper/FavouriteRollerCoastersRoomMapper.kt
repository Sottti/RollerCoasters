package com.sotti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sotti.roller.coasters.data.roller.coasters.datasources.local.model.FavouriteRollerCoasterRoomModel
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId

internal fun RollerCoasterId.toFavouriteRollerCoasterRoomModel(): FavouriteRollerCoasterRoomModel =
    FavouriteRollerCoasterRoomModel(value)
