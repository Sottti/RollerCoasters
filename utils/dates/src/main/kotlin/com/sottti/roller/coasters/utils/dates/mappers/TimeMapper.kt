package com.sottti.roller.coasters.utils.dates.mappers

public fun String.toSeconds(): Int {
    val (minutes, seconds) = split(":").map { string -> string.toInt() }
    return (minutes * 60) + seconds
}