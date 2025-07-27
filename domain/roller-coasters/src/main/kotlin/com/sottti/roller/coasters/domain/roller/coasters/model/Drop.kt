package com.sottti.roller.coasters.domain.roller.coasters.model

import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Meters

public sealed interface Drop {
    public data class ImperialDrop(public val feet: Feet) : Drop
    public data class MetricDrop(public val meters: Meters) : Drop
}
