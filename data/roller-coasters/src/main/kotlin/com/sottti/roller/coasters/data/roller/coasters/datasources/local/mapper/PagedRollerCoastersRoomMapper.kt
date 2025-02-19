package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PagedRollerCoasters
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster

internal fun List<RollerCoaster>.toPagedRollerCoastersRoom(
    pageNumber: PageNumber,
): PagedRollerCoasters = PagedRollerCoasters(
    page = pageNumber.value,
    rollerCoasterIds = map { it.id.value },
)