package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PaginatedRollerCoasters
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster

internal fun List<RollerCoaster>.toPaginatedRollerCoastersRoom(
    pageNumber: PageNumber
): PaginatedRollerCoasters = PaginatedRollerCoasters(
    pageNumber = pageNumber.value,
    rollerCoasterIds = map { it.id.value },
)