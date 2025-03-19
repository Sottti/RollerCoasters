package com.sottti.roller.coasters.domain.roller.coasters.model

public data class RollerCoaster(
    val id: RollerCoasterId,
    val location: Location,
    val name: RollerCoasterName,
    val park: AmusementPark,
    val pictures: Pictures,
    val specs: Specs,
    val status: Status,
)