package com.sottti.roller.coasters.domain.roller.coasters.model

import com.sottti.roller.coasters.domain.model.City
import com.sottti.roller.coasters.domain.model.Coordinates
import com.sottti.roller.coasters.domain.model.Region

public data class Location(
    val city: City,
    val coordinates: Coordinates?,
    val country: Country,
    val region: Region,
    val relocations: Relocations?,
    val state: State,
)