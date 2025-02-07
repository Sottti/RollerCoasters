package com.sottti.roller.coasters.utils.dates.mappers

import com.sottti.roller.coasters.utils.dates.model.Date
import com.sottti.roller.coasters.utils.dates.model.Date.FullDate
import com.sottti.roller.coasters.utils.dates.model.Date.YearAndMonth
import com.sottti.roller.coasters.utils.dates.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private const val FULL_DATE_REGEX = """\d{4}-\d{2}-\d{2}"""  // yyyy-MM-dd
private const val YEAR_MONTH_REGEX = """\d{4}-\d{2}"""       // yyyy-MM
private const val YEAR_ONLY_REGEX = """\d{4}"""              // yyyy

private fun yearMonthFormatter(): DateTimeFormatter? = DateTimeFormatter.ofPattern("yyyy-MM")

public fun String.toDate(): Date =
    when {
        matches(Regex(FULL_DATE_REGEX)) ->
            FullDate(LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE))

        matches(Regex(YEAR_MONTH_REGEX)) ->
            YearAndMonth(YearMonth.parse(this, yearMonthFormatter()))

        matches(Regex(YEAR_ONLY_REGEX)) -> YearOnly(Year.of(toInt()))
        else -> throw IllegalArgumentException("Invalid date format: $this")
    }