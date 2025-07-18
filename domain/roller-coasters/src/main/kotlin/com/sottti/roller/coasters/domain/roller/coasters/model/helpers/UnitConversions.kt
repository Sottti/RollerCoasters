package com.sottti.roller.coasters.domain.roller.coasters.model.helpers

import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.ImperialDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.MetricDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.*
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.*
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.*

public fun Drop.toMetric(): MetricDrop =
    when (this) {
        is ImperialDrop -> MetricDrop(Meters.fromFeet(feet))
        is MetricDrop -> this
    }

public fun Length.toMetric(): MetricLength =
    when (this) {
        is ImperialLength -> MetricLength(Meters.fromFeet(feet))
        is MetricLength -> this
    }

public fun Height.toMetric(): MetricHeight =
    when (this) {
        is ImperialHeight -> MetricHeight(Meters.fromFeet(feet))
        is MetricHeight -> this
    }

public fun Speed.toMetric(): MetricSpeed =
    when (this) {
        is ImperialSpeed -> MetricSpeed(Kmh.fromMph(mph))
        is MetricSpeed -> this
    }