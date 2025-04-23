package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetails

internal fun RollerCoaster.toRollerCoasterDetails(): RollerCoasterDetails =
    RollerCoasterDetails(
        imageUrl = pictures.main?.url,
        parkName = park.name.value,
        rollerCoasterName = name.current.value,
    )