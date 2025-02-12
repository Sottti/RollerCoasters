package com.sottti.roller.coasters.utils.dates.mappers

import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Date.FullDate
import com.sottti.roller.coasters.domain.model.Date.YearAndMonth
import com.sottti.roller.coasters.domain.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private const val FULL_DATE_REGEX = """\d{4}-\d{2}-\d{2}"""  // yyyy-MM-dd
private const val YEAR_MONTH_REGEX = """\d{4}-\d{2}"""       // yyyy-MM
private const val YEAR_ONLY_REGEX = """\d{4}"""              // yyyy

private fun yearMonthFormatter(): DateTimeFormatter? = DateTimeFormatter.ofPattern("yyyy-MM")

public fun String.toDate(): Date? =
    when {
        isEmpty() -> null
        matches(Regex(FULL_DATE_REGEX)) ->
            FullDate(LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE))

        matches(Regex(YEAR_MONTH_REGEX)) ->
            YearAndMonth(YearMonth.parse(this, yearMonthFormatter()))

        matches(Regex(YEAR_ONLY_REGEX)) -> YearOnly(Year.of(toInt()))
        else -> throw IllegalArgumentException("Invalid date format: $this")
    }

private const val FULL_DATE_PATTERN = "yyyy-MM-dd"
private const val YEAR_MONTH_PATTERN = "yyyy-MM"

private val fullDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(FULL_DATE_PATTERN)
private val yearMonthFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(YEAR_MONTH_PATTERN)

public fun Date.toOriginalString(): String =
    when (this) {
        is FullDate -> this.localDate.format(fullDateFormatter)
        is YearAndMonth -> this.yearMonth.format(yearMonthFormatter)
        is YearOnly -> this.year.value.toString()
    }