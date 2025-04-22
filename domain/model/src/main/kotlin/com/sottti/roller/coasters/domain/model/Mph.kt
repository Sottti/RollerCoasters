package com.sottti.roller.coasters.domain.model

@JvmInline
public value class Mph(public val value: Double) {

    public companion object {
        private const val KPH_TO_MPH = 0.621371

        public fun fromKph(kph: Kmh): Mph =
            Mph(kph.value * KPH_TO_MPH)
    }
}