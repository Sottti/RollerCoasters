package com.sottti.roller.coasters.data.roller.coasters.model

public data class Status(
    val closedDate: CloseDate?,
    val current: OperationalState,
    val former: FormerStatus?,
    val openedDate: OpenDate?,
)