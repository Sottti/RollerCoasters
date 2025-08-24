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

private const val YEAR_ONLY_SORTABLE_SUFFIX = "-00-00"
private const val YEAR_AND_MONTH_SORTABLE_SUFFIX = "-00"

@VisibleForTesting
internal const val INVALID_DATE_FORMAT_MESSAGE = "Invalid date format: %s"

@VisibleForTesting
internal const val EMPTY_OR_BLANK_DATE_MESSAGE = "Input date string cannot be empty or blank"

private val FULL_DATE_REGEX_PATTERN = Regex("""\d{4}-\d{2}-\d{2}""")
private val YEAR_MONTH_REGEX_PATTERN = Regex("""\d{4}-\d{1,2}""")
private val YEAR_ONLY_REGEX_PATTERN = Regex("""-?\d{4}""")

private val fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-M")
private val yearMonthSortableFormatter = DateTimeFormatter.ofPattern("yyyy-MM")

@VisibleForTesting
internal fun invalidFullDateMessage(input: String) = "Invalid full date value: $input"

@VisibleForTesting
internal fun invalidYearMonthMessage(input: String) = "Invalid year-month value: $input"

@VisibleForTesting
internal fun invalidYearOnlyMessage(input: String) = "Invalid year value: $input"

public fun String.toDate(): Date? {
    if (this.isBlank()) return null
    val cleanedDate = cleanDate()
    return when {
        cleanedDate.matches(FULL_DATE_REGEX_PATTERN) -> cleanedDate.toFullDate()
        cleanedDate.matches(YEAR_MONTH_REGEX_PATTERN) -> cleanedDate.toYearAndMonth()
        cleanedDate.matches(YEAR_ONLY_REGEX_PATTERN) -> cleanedDate.toYearOnly()
        else -> throw IllegalArgumentException(INVALID_DATE_FORMAT_MESSAGE.format(this))
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
        .also { require (it.isNotBlank()) { EMPTY_OR_BLANK_DATE_MESSAGE } }

private fun String.toFullDate(): Date = runCatching {
    FullDate(LocalDate.parse(this, fullDateFormatter))
}.getOrElse { throw IllegalArgumentException(invalidFullDateMessage(this), it) }

private fun String.toYearAndMonth(): Date = runCatching {
    YearAndMonth(YearMonth.parse(this, yearMonthFormatter))
}.getOrElse { throw IllegalArgumentException(invalidYearMonthMessage(this), it) }

private fun String.toYearOnly(): Date = runCatching {
    YearOnly(Year.of(this.toInt()))
}.getOrElse { throw IllegalArgumentException(invalidYearOnlyMessage(this), it) }