package com.sottti.roller.coasters.domain.model

public data class Status(
    val closedDate: ClosedDate?,
    val current: OperationalState,
    val former: FormerStatus?,
    val openedDate: OpenedDate?,
)