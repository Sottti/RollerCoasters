package com.sotti.roller.coasters.presentation.favourites.data

import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster

internal fun RollerCoaster.toUiModel(): FavouritesRollerCoaster =
    FavouritesRollerCoaster(
        id = id.value,
        imageUrl = pictures.main?.url,
        name = name.current.value,
        parkName = park.name.value,
    )
