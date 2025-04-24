package com.sottti.roller.coasters.presentation.format

import android.content.Context
import com.sottti.roller.coasters.domain.model.Date
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import javax.inject.Inject

private const val YEAR_MONTH_PATTERN = "MMMM yyyy"

public class DateFormatter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val locale: Locale,
) {
    public fun format(date: Date): String =
        when (date) {
            is Date.FullDate -> format(date.localDate)
            is Date.YearAndMonth -> format(date.yearMonth)
            is Date.YearOnly -> format(date.year)
        }

    private fun format(localDate: LocalDate): String {
        val formatter = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(locale)
        return localDate.format(formatter)
    }

    private fun format(yearMonth: YearMonth): String {
        val formatter = DateTimeFormatter
            .ofPattern(YEAR_MONTH_PATTERN)
            .withLocale(locale)
        return yearMonth.format(formatter)
    }

    private fun format(year: Year): String = year.value.toString()
}