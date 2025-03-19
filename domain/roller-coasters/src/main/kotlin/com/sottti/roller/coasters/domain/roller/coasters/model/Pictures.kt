package com.sottti.roller.coasters.domain.roller.coasters.model

import com.sottti.roller.coasters.domain.model.Picture

public data class Pictures(
    val main: Picture?,
    val other: List<Picture>,
)