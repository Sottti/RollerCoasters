package com.sottti.roller.coasters.data.roller.coasters.model

public data class RollerCoaster(
    val id: Id,
    val location: Location,
    val name: RollerCoasterName,
    val park: AmusementPark,
    val pictures: Pictures,
    val specs: Specs,
    val status: Status,
)