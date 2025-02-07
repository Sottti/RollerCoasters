package com.sottti.roller.coasters.data.roller.coasters.model

public data class Status(
    val state: OperationalState,
    val openedDate: OpenDate,
    val closedDate: CloseDate?,
)