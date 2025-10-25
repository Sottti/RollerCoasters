package com.sotti.roller.coasters.presentation.favourites.fixtures

import com.sotti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sotti.roller.coasters.domain.fixtures.rollerCoaster
import com.sotti.roller.coasters.presentation.favourites.data.toUiModel

internal fun favouritesRollerCoasters() = listOf(
    rollerCoaster().toUiModel(),
    anotherRollerCoaster().toUiModel(),
)
