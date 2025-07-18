package com.sottti.roller.coasters.data.roller.coasters.mapper

import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Mph
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.ImperialDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.MetricDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.ImperialHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.MetricHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.ImperialLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.MetricLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.ImperialSpeed
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.MetricSpeed
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric

internal fun Double.toSpeed(
    measurementSystem: ResolvedMeasurementSystem,
): Speed = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialSpeed(Mph.fromKph(Kmh(this)))
    Metric -> MetricSpeed(Kmh(this))
}

internal fun Double.toHeight(
    measurementSystem: ResolvedMeasurementSystem,
): Height = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialHeight(Feet.fromMeters(Meters(this)))
    Metric -> MetricHeight(Meters(this))
}

internal fun Double.toDrop(
    measurementSystem: ResolvedMeasurementSystem,
): Drop = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialDrop(Feet.fromMeters(Meters(this)))
    Metric -> MetricDrop(Meters(this))
}

internal fun Double.toLength(
    measurementSystem: ResolvedMeasurementSystem,
): Length = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialLength(Feet.fromMeters(Meters(this)))
    Metric -> MetricLength(Meters(this))
}