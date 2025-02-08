package com.sottti.roller.coasters.data.roller.coasters.model

public data class RollerCoaster(
    val id: Id,
    val name: Name,
    val park: AmusementPark,
    val location: Location,
    val status: Status,
    val manufacturer: Manufacturer,
    val model: Model,
    val type: Type,
    val design: Design,
    val stats: Stats?,
    val pictures: Pictures,
)