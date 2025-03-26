package com.sottti.roller.coasters.utils.time.dates.mapper

public fun String.toSeconds(): Int {
    val parts = trim().split(":")
    if (parts.size != 2)
        throw IllegalArgumentException("Time must be in MM:SS format")
    val (minutes, seconds) = parts.map { it.toInt() }
    if (minutes < 0 || seconds < 0)
        throw IllegalArgumentException("Minutes and seconds must be non-negative")
    return (minutes * 60) + seconds
}