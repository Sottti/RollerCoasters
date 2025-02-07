package com.sottti.roller.coasters.utils.dates.mappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

public fun String.toLocalDate(): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)