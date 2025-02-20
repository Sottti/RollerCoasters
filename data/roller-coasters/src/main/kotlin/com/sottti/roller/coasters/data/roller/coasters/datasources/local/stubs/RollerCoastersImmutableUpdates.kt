package com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun RollerCoasterRoomModel.withMaxHeight(
    maxHeight: Double?,
): RollerCoasterRoomModel = copy(
    specs = specs.copy(
        ride = specs.ride?.copy(
            maxHeight = maxHeight,
        )
    )
)

@OptIn(InternalSerializationApi::class)
internal fun RollerCoasterRoomModel.withId(
    id: Int,
): RollerCoasterRoomModel = copy(id = id)