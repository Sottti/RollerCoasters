package com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PagedRollerCoasters
import kotlinx.serialization.InternalSerializationApi

internal const val PAGE_NUMBER_INITIAL = 0
internal const val PAGE_NUMBER_SECOND = 1
internal const val PAGE_NUMBER_THIRD = 2

@OptIn(InternalSerializationApi::class)
internal val pagedRollerCoastersRoom =
    PagedRollerCoasters(
        page = PAGE_NUMBER_INITIAL,
        rollerCoasterIds = listOf(rollerCoasterRoomModel.id),
    )

@OptIn(InternalSerializationApi::class)
internal val anotherPagedRollerCoastersRoom =
    PagedRollerCoasters(
        page = PAGE_NUMBER_SECOND,
        rollerCoasterIds = listOf(anotherRollerCoasterRoomModel.id),
    )