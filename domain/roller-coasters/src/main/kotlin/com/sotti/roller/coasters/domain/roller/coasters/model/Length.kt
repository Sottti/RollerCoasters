package com.sotti.roller.coasters.domain.roller.coasters.model

import com.sotti.roller.coasters.domain.model.Feet
import com.sotti.roller.coasters.domain.model.Meters

public sealed interface Length {
    public data class ImperialLength(public val feet: Feet) : Length
    public data class MetricLength(public val meters: Meters) : Length
}
