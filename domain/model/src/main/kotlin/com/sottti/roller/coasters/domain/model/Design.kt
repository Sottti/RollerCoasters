package com.sottti.roller.coasters.domain.model

public data class Design(
    val arrangement: Arrangement?,
    val designer: Designer?,
    val elements: Element?,
    val restraints: Restraints?,
    val train: Train,
    val type: Type,
)