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
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric

internal fun Double.toSpeed(
    measurementSystem: SystemMeasurementSystem,
): Speed = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialSpeed(Mph.fromKph(Kmh(this)))
    Metric -> MetricSpeed(Kmh(this))
}

internal fun Double.toHeight(
    measurementSystem: SystemMeasurementSystem,
): Height = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialHeight(Feet.fromMeters(Meters(this)))
    Metric -> MetricHeight(Meters(this))
}

internal fun Double.toDrop(
    measurementSystem: SystemMeasurementSystem,
): Drop = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialDrop(Feet.fromMeters(Meters(this)))
    Metric -> MetricDrop(Meters(this))
}

internal fun Double.toLength(
    measurementSystem: SystemMeasurementSystem,
): Length = when (measurementSystem) {
    ImperialUk, ImperialUs -> ImperialLength(Feet.fromMeters(Meters(this)))
    Metric -> MetricLength(Meters(this))
}

internal fun Drop.toMetric(): MetricDrop =
    when (this) {
        is ImperialDrop -> MetricDrop(Meters.fromFeet(feet))
        is MetricDrop -> this
    }

internal fun Length.toMetric(): MetricLength =
    when (this) {
        is ImperialLength -> MetricLength(Meters.fromFeet(feet))
        is MetricLength -> this
    }

internal fun Height.toMetric(): MetricHeight =
    when (this) {
        is ImperialHeight -> MetricHeight(Meters.fromFeet(feet))
        is MetricHeight -> this
    }

internal fun Speed.toMetric(): MetricSpeed =
    when (this) {
        is ImperialSpeed -> MetricSpeed(Kmh.fromMph(mph))
        is MetricSpeed -> this
    }