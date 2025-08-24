package com.sottti.roller.coasters.utils.time.dates.mapper

import androidx.annotation.VisibleForTesting


@VisibleForTesting
internal const val INVALID_TIME_FORMAT_MESSAGE = "Time must be in MM:SS format"

@VisibleForTesting
internal const val NEGATIVE_TIME_MESSAGE = "Minutes and seconds must be non-negative"

public fun String.toSeconds(): Int {
    val parts = trim().split(":")
    require(parts.size == 2) { INVALID_TIME_FORMAT_MESSAGE }
    val (minutes, seconds) = parts.map { it.trim().toInt() }
    require(minutes >= 0 || seconds >= 0) { NEGATIVE_TIME_MESSAGE }
    return (minutes * 60) + seconds
}