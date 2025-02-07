package com.sottti.roller.coasters.data.roller.coasters.model

public data class Statistics(
    val arrangement: RollerCoasterArrangement?,
    val capacity: Capacity?,
    val cost: Cost?,
    val designer: Designer?,
    val dimensions: Dimensions?,
    val drop: Drop?,
    val duration: Duration?,
    val elements: List<RollerCoasterElement>?,
    val formerNames: FormerNames?,
    val formerStatus: FormerStatus?,
    val gForce: GForce?,
    val height: Height?,
    val inversions: Inversions?,
    val length: Length?,
    val relocations: Relocations?,
    val speed: Speed?,
)