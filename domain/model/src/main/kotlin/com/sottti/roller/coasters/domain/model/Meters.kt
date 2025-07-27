package com.sottti.roller.coasters.domain.model

@JvmInline
public value class Meters(public val value: Double) {

    public companion object {
        private const val FEET_TO_METERS = 0.3048

        public fun fromFeet(feet: Feet): Meters =
            Meters(feet.value * FEET_TO_METERS)
    }
}
