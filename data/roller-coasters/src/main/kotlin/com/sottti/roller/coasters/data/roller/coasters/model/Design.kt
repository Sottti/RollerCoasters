package com.sottti.roller.coasters.data.roller.coasters.model

public data class Design(
    val arrangement: RollerCoasterArrangement?,
    val designer: Designer?,
    val elements: RollerCoasterElement?,
    val restraints: Restraints?,
    val train: Train,
    val type: Type,
)