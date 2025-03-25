package com.sottti.roller.coasters.domain.roller.coasters.model

import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Meters

public sealed class Length {
    public data class ImperialLength(public val feet: Feet) : Length()
    public data class MetricLength(public val meters: Meters) : Length()
}