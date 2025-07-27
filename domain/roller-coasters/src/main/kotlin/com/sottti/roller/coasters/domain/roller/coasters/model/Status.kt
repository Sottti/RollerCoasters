package com.sottti.roller.coasters.domain.roller.coasters.model

public data class Status(
    val closedDate: ClosedDate?,
    val current: OperationalState?,
    val former: FormerStatus?,
    val openedDate: OpenedDate?,
) {
    public fun containsData(): Boolean =
        closedDate != null || current != null || former != null || openedDate != null
}
