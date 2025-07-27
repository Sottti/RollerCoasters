package com.sottti.roller.coasters.domain.model

@JvmInline
public value class Kmh(public val value: Double) {

    public companion object {
        private const val MPH_TO_KMH = 1.60934

        public fun fromMph(mph: Mph): Kmh =
            Kmh(mph.value * MPH_TO_KMH)
    }
}
