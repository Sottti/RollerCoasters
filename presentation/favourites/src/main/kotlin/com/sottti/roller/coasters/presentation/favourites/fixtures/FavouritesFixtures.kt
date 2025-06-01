package com.sottti.roller.coasters.presentation.favourites.fixtures

import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster

internal fun favouritesRollerCoasters() =
    listOf(
        favouriteRollerCoaster(),
        anotherFavouriteRollerCoaster(),
    )

private fun favouriteRollerCoaster(): FavouritesRollerCoaster =
    FavouritesRollerCoaster(
        id = rollerCoaster().id.value,
        imageUrl = rollerCoaster().pictures.main?.url,
        name = rollerCoaster().name.current.value,
        parkName = rollerCoaster().park.name.value,
    )

private fun anotherFavouriteRollerCoaster(): FavouritesRollerCoaster =
    FavouritesRollerCoaster(
        id = rollerCoaster().id.value,
        imageUrl = anotherRollerCoaster().pictures.main?.url,
        name = anotherRollerCoaster().name.current.value,
        parkName = anotherRollerCoaster().park.name.value,
    )