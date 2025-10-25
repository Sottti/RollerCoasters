package com.sotti.roller.coasters.domain.roller.coasters.model

import com.sotti.roller.coasters.domain.model.Feet
import com.sotti.roller.coasters.domain.model.Meters

public sealed interface Height {
    public data class ImperialHeight(public val feet: Feet) : Height
    public data class MetricHeight(public val meters: Meters) : Height
}
