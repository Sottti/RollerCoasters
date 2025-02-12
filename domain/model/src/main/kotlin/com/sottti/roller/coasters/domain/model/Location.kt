package com.sottti.roller.coasters.domain.model

public data class Location(
    val city: City,
    val coordinates: Coordinates?,
    val country: Country,
    val region: Region,
    val relocations: Relocations?,
    val state: State,
)