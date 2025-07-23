package com.sottti.roller.coasters.utils.time.dates.mappers

import com.sottti.roller.coasters.domain.model.Date.FullDate
import com.sottti.roller.coasters.domain.model.Date.YearAndMonth
import com.sottti.roller.coasters.domain.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

internal val fullDate = FullDate(LocalDate.of(2018, 6, 6))
internal const val FULL_DATE_SORTABLE_STRING = "2018-06-06"

internal val yearAndMonth = YearAndMonth(YearMonth.of(2023, 10))
internal const val NO_SUFFIX_YEAR_MONTH_STRING = "2023-10"
internal const val YEAR_AND_MONTH_SORTABLE_STRING = "2023-10-00"

internal val yearOnly = YearOnly(Year.of(1984))
internal const val NO_SUFFIX_YEAR_STRING = "1984"
internal const val YEAR_ONLY_SORTABLE_STRING = "1984-00-00"

internal val sortedMixedDates = arrayOf(
    "1984-00-00", // 1984
    "1999-00-00", // 1999
    "2005-02-01", // 2005 February 1st
    "2011-08-00", // 2011 August
    "2018-06-06", // 2018 June 6th
    "2023-10-00"  // 2023 October,
)
internal val unsortedMixedDates = listOf(
    fullDate, // 2018-06-06
    yearAndMonth, // 2023-10-00
    yearOnly, // 1984-00-00
    FullDate(LocalDate.of(2005, 2, 1)), // 2005-02-01
    YearAndMonth(YearMonth.of(2011, 8)), // 2011-08-00
    YearOnly(Year.of(1999)) // 1999-00-00
)

internal val sortedMixedSuffixDates = arrayOf(
    "1984-00-00", // 1984
    "1999-00-00", // 1999
    "2005-02-01", // 2005 February 1st
    "2011-08-00", // 2011 August
    "2018-06-06", // 2018 June 6th
    "2023-10-00"  // 2023 October
)
internal val unsortedMixedSuffixDates = listOf(
    FullDate(LocalDate.of(2018, 6, 6)), // 2018-06-06
    YearAndMonth(YearMonth.of(2023, 10)), // 2023-10-00
    YearOnly(Year.of(1984)), // 1984-00-00
    YearAndMonth(YearMonth.of(2011, 8)), // 2011-08-00 (with suffix)
    YearOnly(Year.of(1999)), // 1999-00-00 (with suffix)
    FullDate(LocalDate.of(2005, 2, 1)) // 2005-02-01
)

internal const val EMPTY_STRING = ""
internal const val EXTREME_NEGATIVE_YEAR_SORTABLE_STRING = "-999999999"
internal const val EXTREME_POSITIVE_YEAR_SORTABLE_STRING = "999999999"
internal const val INTERNAL_WHITESPACE_DATE = "2023- 10-01"
internal const val INVALID_DAY_FULL_DATE_STRING = "2022-02-60"
internal const val INVALID_MONTH_YEAR_MONTH = "1984-00"
internal const val INVALID_YEAR_MONTH_INVALID_MONTH = "2023-13"
internal const val LEAP_YEAR_FULL_DATE_STRING = "2020-02-29"
internal const val MAX_FULL_DATE_SORTABLE_STRING = "9999-12-31"
internal const val MAX_YEAR_ONLY_STRING = "9999-00-00"
internal const val MIN_FULL_DATE_SORTABLE_STRING = "0001-01-01"
internal const val MIN_YEAR_ONLY_STRING = "0001-00-00"
internal const val NEGATIVE_YEAR_SORTABLE_STRING = "-2023-00-00"
internal const val PARTIAL_SUFFIX_FULL_DATE_STRING = "2023-10-01-0"
internal const val PARTIAL_SUFFIX_YEAR_MONTH = "2023-10-0"
internal const val RANDOM_TEXT = "random-text"
internal const val SINGLE_DIGIT_MONTH_STRING = "2023-6"
internal const val WHITESPACE_FULL_DATE_STRING = "  2018-06-06"
internal const val WHITESPACE_YEAR_MONTH_STRING = "2023-10  "
internal const val WHITESPACE_YEAR_STRING = "  1984  "
internal const val ZERO_YEAR_SORTABLE_STRING = "0000"
internal val leapYearFullDate = FullDate(LocalDate.of(2020, 2, 29))
internal val maxFullDate = FullDate(LocalDate.of(9999, 12, 31))
internal val maxYearOnly = YearOnly(Year.of(9999))
internal val minFullDate = FullDate(LocalDate.of(1, 1, 1))
internal val minYearOnly = YearOnly(Year.of(1))
internal val negativeYearOnly = YearOnly(Year.of(-2023))
internal val singleDigitMonth = YearAndMonth(YearMonth.of(2023, 6))
internal val zeroYearOnly = YearOnly(Year.of(0))

