package com.sottti.roller.coasters.domain.roller.coasters.model.helpers

import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.Duration
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Ride
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed

public fun Ride.maxDuration(): Duration? = when (this) {
    is MultiTrackRide -> duration?.maxByOrNull { it.seconds.value }
    is SingleTrackRide -> duration
}

public fun Ride.maxDrop(): Drop? = when (this) {
    is MultiTrackRide -> drop?.maxByOrNull { it.toMetric().meters.value }
    is SingleTrackRide -> drop
}

public fun Ride.maxGForce(): GForce? = when (this) {
    is MultiTrackRide -> gForce?.maxByOrNull { it.value }
    is SingleTrackRide -> gForce
}

public fun Ride.maxHeight(): Height? = when (this) {
    is MultiTrackRide -> height?.maxByOrNull { it.toMetric().meters.value }
    is SingleTrackRide -> height
}

public fun Ride.maxInversions(): Inversions? = when (this) {
    is MultiTrackRide -> inversions?.maxByOrNull { it.value }
    is SingleTrackRide -> inversions
}

public fun Ride.maxLength(): Length? = when (this) {
    is MultiTrackRide -> length?.maxByOrNull { it.toMetric().meters.value }
    is SingleTrackRide -> length
}

public fun Ride.maxMaxVertical(): MaxVertical? = when (this) {
    is MultiTrackRide -> maxVertical?.maxByOrNull { it.degrees.value }
    is SingleTrackRide -> maxVertical
}

public fun Ride.maxSpeed(): Speed? = when (this) {
    is MultiTrackRide -> speed?.maxByOrNull { it.toMetric().kmh.value }
    is SingleTrackRide -> speed
}
