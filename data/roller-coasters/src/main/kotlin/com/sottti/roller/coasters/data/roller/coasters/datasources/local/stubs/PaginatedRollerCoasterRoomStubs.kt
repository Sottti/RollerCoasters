package com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PaginatedRollerCoasters
import com.sottti.roller.coasters.domain.model.PageNumber
import kotlinx.serialization.InternalSerializationApi

internal const val PAGE_NUMBER = 1
internal val pageNumber = PageNumber(PAGE_NUMBER)
internal val pageSize = 1

@OptIn(InternalSerializationApi::class)
internal val paginatedRollerCoastersRoom =
    PaginatedRollerCoasters(
        pageNumber = PAGE_NUMBER,
        rollerCoasterIds = rollerCoastersRoom.map { it.id },
    )

@OptIn(InternalSerializationApi::class)
internal val anotherPaginatedRollerCoastersRoom =
    PaginatedRollerCoasters(
        pageNumber = PAGE_NUMBER + 1,
        rollerCoasterIds = rollerCoastersRoom.take(3).map { it.id }
    )

internal val emptyPaginatedRollerCoastersRoom =
    PaginatedRollerCoasters(
        pageNumber = PAGE_NUMBER,
        rollerCoasterIds = emptyList(),
    )