package com.sottti.roller.coasters.domain.model

@JvmInline
public value class Feet(public val value: Double) {

    public companion object {
        private const val METERS_TO_FEET = 3.28084

        public fun fromMeters(meters: Meters): Feet =
            Feet(meters.value * METERS_TO_FEET)
    }
}
