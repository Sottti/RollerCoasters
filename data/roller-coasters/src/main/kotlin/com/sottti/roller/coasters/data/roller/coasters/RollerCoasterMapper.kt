package com.sottti.roller.coasters.data.roller.coasters

import com.sottti.roller.coasters.data.roller.coasters.datasources.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoasterName

internal fun RollerCoasterApiModel.toDomainModel(): RollerCoaster =
    RollerCoaster(
        RollerCoasterName(name)
    )