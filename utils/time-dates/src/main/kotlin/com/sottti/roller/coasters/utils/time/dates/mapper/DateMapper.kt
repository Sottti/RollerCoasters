package com.sottti.roller.coasters.utils.time.dates.mapper

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Date.FullDate
import com.sottti.roller.coasters.domain.model.Date.YearAndMonth
import com.sottti.roller.coasters.domain.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@VisibleForTesting
internal const val YEAR_ONLY_SORTABLE_SUFFIX = "-00-00"
private const val YEAR_AND_MONTH_SORTABLE_SUFFIX = "-00"

private val FULL_DATE_REGEX_PATTERN = Regex("""\d{4}-\d{2}-\d{2}""")
private val YEAR_MONTH_REGEX_PATTERN = Regex("""\d{4}-\d{1,2}""")
private val YEAR_ONLY_REGEX_PATTERN = Regex("""-?\d{4}""")

private val fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-M")
private val yearMonthSortableFormatter = DateTimeFormatter.ofPattern("yyyy-MM")

public fun String.toDate(): Date {
    val cleanedDate = cleanDate()
    return when {
        cleanedDate.matches(FULL_DATE_REGEX_PATTERN) -> cleanedDate.toFullDate()
        cleanedDate.matches(YEAR_MONTH_REGEX_PATTERN) -> cleanedDate.toYearAndMonth()
        cleanedDate.matches(YEAR_ONLY_REGEX_PATTERN) -> cleanedDate.toYearOnly()
        else -> throw IllegalArgumentException("Invalid date format: $this")
    }
}

public fun Date.toSortableString(): String =
    when (this) {
        is FullDate -> localDate.format(fullDateFormatter)
        is YearAndMonth -> "${yearMonth.format(yearMonthSortableFormatter)}$YEAR_AND_MONTH_SORTABLE_SUFFIX"
        is YearOnly -> "${year.value}$YEAR_ONLY_SORTABLE_SUFFIX"
    }

private fun String.cleanDate(): String =
    trim()
        .removeSuffix(YEAR_ONLY_SORTABLE_SUFFIX)
        .removeSuffix(YEAR_AND_MONTH_SORTABLE_SUFFIX)
        .also { if (it.isEmpty()) throw IllegalArgumentException("Input date string cannot be empty or blank") }

private fun String.toFullDate(): Date = runCatching {
    FullDate(LocalDate.parse(this, fullDateFormatter))
}.getOrElse { throw IllegalArgumentException("Invalid full date value: $this", it) }

private fun String.toYearAndMonth(): Date = runCatching {
    YearAndMonth(YearMonth.parse(this, yearMonthFormatter))
}.getOrElse { throw IllegalArgumentException("Invalid year-month value: $this", it) }

private fun String.toYearOnly(): Date = runCatching {
    YearOnly(Year.of(this.toInt()))
}.getOrElse { throw IllegalArgumentException("Invalid year value: $this", it) }