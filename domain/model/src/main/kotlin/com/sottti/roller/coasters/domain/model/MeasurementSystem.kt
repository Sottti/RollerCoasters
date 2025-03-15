package com.sottti.roller.coasters.domain.model

public sealed class MeasurementSystem {
    public data object ImperialUK : MeasurementSystem()
    public data object ImperialUS : MeasurementSystem()
    public data object Metric : MeasurementSystem()
    public data object System : MeasurementSystem()
}