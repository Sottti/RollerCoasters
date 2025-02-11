package com.sottti.roller.coasters.data.roller.coasters.model

public sealed class Ride

public data class SingleTrackRide(
    val drop: Drop?,
    val duration: Duration?,
    val gForce: GForce?,
    val height: Height?,
    val inversions: Inversions?,
    val length: Length?,
    val maxVertical: MaxVertical?,
    val speed: Speed?,
) : Ride()

public data class MultiTrackRide(
    val drop: List<Drop>?,
    val duration: List<Duration>?,
    val gForce: List<GForce>?,
    val height: List<Height>?,
    val inversions: List<Inversions>?,
    val length: List<Length>?,
    val maxVertical: List<MaxVertical>?,
    val speed: List<Speed>?,
    val trackNames: List<Name>?,
) : Ride()