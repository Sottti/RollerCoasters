package com.sottti.roller.coasters.utils.time.dates.mappers

import com.sottti.roller.coasters.domain.model.Date.FullDate
import com.sottti.roller.coasters.domain.model.Date.YearAndMonth
import com.sottti.roller.coasters.domain.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

internal val fullDate = FullDate(LocalDate.of(2018, 6, 6))
internal const val fullDateSortableString = "2018-06-06"

internal val yearAndMonth = YearAndMonth(YearMonth.of(2023, 10))
internal const val yearAndMonthSortableString = "2023-10-00"

internal val yearOnly = YearOnly(Year.of(1984))
internal const val yearOnlySortableString = "1984-00-00"

internal const val invalidDate = "2022-15-40"
internal const val randomText = "random-text"

internal val unsortedMixedDates = listOf(
    fullDate, // 2018-06-06
    yearAndMonth, // 2023-10-00
    yearOnly, // 1984-00-00
    FullDate(LocalDate.of(2005, 2, 1)), // 2005-02-01
    YearAndMonth(YearMonth.of(2011, 8)), // 2011-08-00
    YearOnly(Year.of(1999)) // 1999-00-00
)

internal val sortedMixedDates = arrayOf(
    "1984-00-00", // 1984
    "1999-00-00", // 1999
    "2005-02-01", // 2005 February 1st
    "2011-08-00", // 2011 August
    "2018-06-06", // 2018 June 6th
    "2023-10-00"  // 2023 October
)