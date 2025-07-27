package com.sottti.roller.coasters.presentation.favourites.fixtures

import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.favourites.data.toUiModel

internal fun favouritesRollerCoasters() = listOf(
    rollerCoaster().toUiModel(),
    anotherRollerCoaster().toUiModel(),
)
