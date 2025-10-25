package com.sotti.roller.coasters.domain.roller.coasters.model

import com.sotti.roller.coasters.domain.model.City
import com.sotti.roller.coasters.domain.model.Coordinates

public data class Location(
    val city: City,
    val coordinates: Coordinates?,
    val country: Country,
    val relocations: Relocations?,
)
