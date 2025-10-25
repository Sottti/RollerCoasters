package com.sotti.roller.coasters.domain.roller.coasters.model

import com.sotti.roller.coasters.domain.model.Kmh
import com.sotti.roller.coasters.domain.model.Mph

public sealed interface Speed {
    public data class ImperialSpeed(public val mph: Mph) : Speed
    public data class MetricSpeed(public val kmh: Kmh) : Speed
}
