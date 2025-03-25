package com.sottti.roller.coasters.utils.time.dates.mapper

import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.domain.model.Date.FullDate
import com.sottti.roller.coasters.domain.model.Date.YearAndMonth
import com.sottti.roller.coasters.domain.model.Date.YearOnly
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private const val YEAR_AND_MONTH_SORTABLE_SUFFIX = "-00"
private const val YEAR_ONLY_SORTABLE_SUFFIX = "-00-00"

private const val FULL_DATE_REGEX = """\d{4}-\d{2}-\d{2}"""  // yyyy-MM-dd
private const val YEAR_MONTH_REGEX = """\d{4}-\d{2}"""       // yyyy-MM
private const val YEAR_ONLY_REGEX = """\d{4}"""              // yyyy

private const val FULL_DATE_PATTERN = "yyyy-MM-dd"
private const val YEAR_MONTH_PATTERN = "yyyy-MM"
private val fullDateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern(FULL_DATE_PATTERN)
private val yearMonthFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern(YEAR_MONTH_PATTERN)

public fun String.toDate(): Date? {
    if (isEmpty()) return null

    val cleanedDate = removeSuffix(YEAR_ONLY_SORTABLE_SUFFIX)
        .removeSuffix(YEAR_AND_MONTH_SORTABLE_SUFFIX)


    return when {
        cleanedDate.matches(Regex(FULL_DATE_REGEX)) ->
            FullDate(LocalDate.parse(cleanedDate, fullDateFormatter))

        cleanedDate.matches(Regex(YEAR_MONTH_REGEX)) ->
            YearAndMonth(YearMonth.parse(cleanedDate, yearMonthFormatter))

        cleanedDate.matches(Regex(YEAR_ONLY_REGEX)) ->
            YearOnly(Year.of(cleanedDate.toInt()))

        else -> throw IllegalArgumentException("Invalid date format: $this")
    }
}

public fun Date.toSortableString(): String =
    when (this) {
        is FullDate -> localDate.format(fullDateFormatter)
        is YearAndMonth -> "${yearMonth.format(yearMonthFormatter)}$YEAR_AND_MONTH_SORTABLE_SUFFIX"
        is YearOnly -> "${year.value}$YEAR_ONLY_SORTABLE_SUFFIX"
    }