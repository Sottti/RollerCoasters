package com.sottti.roller.coasters.data.roller.coasters.model

public data class Location(
    val city: City,
    val coordinates: Coordinates?,
    val country: Country,
    val region: Region,
    val relocations: Relocations?,
    val state: State,
)