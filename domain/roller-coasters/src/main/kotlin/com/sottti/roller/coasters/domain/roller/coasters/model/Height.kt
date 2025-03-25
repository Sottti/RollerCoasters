package com.sottti.roller.coasters.domain.roller.coasters.model

import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Meters

public sealed class Height {
    public data class ImperialHeight(public val feet: Feet) : Height()
    public data class MetricHeight(public val meters: Meters) : Height()
}