package com.sottti.roller.coasters.domain.model

public sealed class MeasurementSystem {
    public data object ImperialUk : MeasurementSystem()
    public data object ImperialUs : MeasurementSystem()
    public data object Metric : MeasurementSystem()
    public data object System : MeasurementSystem()
}