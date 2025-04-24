package com.sottti.roller.coasters.presentation.format

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Date
import com.sottti.roller.coasters.presentation.fixtures.localeEs
import com.sottti.roller.coasters.presentation.fixtures.localeFr
import com.sottti.roller.coasters.presentation.fixtures.localeUs
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

internal class DateFormatterTest {

    private val context: Context = mockk()

    private val localDate = LocalDate.of(2025, 4, 24)
    private val yearMonth = YearMonth.of(2025, 4)
    private val year2025 = Year.of(2025)
    private val year1999 = Year.of(1999)

    private lateinit var formatterUs: DateFormatter
    private lateinit var formatterEs: DateFormatter
    private lateinit var formatterFr: DateFormatter

    companion object {
        private const val FULL_DATE_US = "Apr 24, 2025"
        private const val FULL_DATE_ES = "24 abr 2025"
        private const val FULL_DATE_FR = "24 avr. 2025"

        private const val YEAR_MONTH_US = "April 2025"
        private const val YEAR_MONTH_ES = "abril 2025"
        private const val YEAR_MONTH_FR = "avril 2025"

        private const val YEAR_2025 = "2025"
        private const val YEAR_1999 = "1999"
    }

    @Before
    fun setUp() {
        formatterUs = DateFormatter(context, localeUs)
        formatterEs = DateFormatter(context, localeEs)
        formatterFr = DateFormatter(context, localeFr)
    }

    @Test
    fun `format full date with US locale should return correct format`() {
        val date = Date.FullDate(localDate)
        val formatted = formatterUs.format(date)
        assertThat(formatted).isEqualTo(FULL_DATE_US)
    }

    @Test
    fun `format full date with ES locale should return correct format`() {
        val date = Date.FullDate(localDate)
        val formatted = formatterEs.format(date)
        assertThat(formatted).isEqualTo(FULL_DATE_ES)
    }

    @Test
    fun `format full date with FR locale should return correct format`() {
        val date = Date.FullDate(localDate)
        val formatted = formatterFr.format(date)
        assertThat(formatted).isEqualTo(FULL_DATE_FR)
    }

    @Test
    fun `format year and month with US locale should return correct format`() {
        val date = Date.YearAndMonth(yearMonth)
        val formatted = formatterUs.format(date)
        assertThat(formatted).isEqualTo(YEAR_MONTH_US)
    }

    @Test
    fun `format year and month with ES locale should return correct format`() {
        val date = Date.YearAndMonth(yearMonth)
        val formatted = formatterEs.format(date)
        assertThat(formatted).isEqualTo(YEAR_MONTH_ES)
    }

    @Test
    fun `format year and month with FR locale should return correct format`() {
        val date = Date.YearAndMonth(yearMonth)
        val formatted = formatterFr.format(date)
        assertThat(formatted).isEqualTo(YEAR_MONTH_FR)
    }

    @Test
    fun `format year only should return correct format regardless of locale`() {
        val date = Date.YearOnly(year2025)
        val formattedUs = formatterUs.format(date)
        val formattedEs = formatterEs.format(date)
        val formattedFr = formatterFr.format(date)

        assertThat(formattedUs).isEqualTo(YEAR_2025)
        assertThat(formattedEs).isEqualTo(YEAR_2025)
        assertThat(formattedFr).isEqualTo(YEAR_2025)
    }

    @Test
    fun `format year only with different year should return correct format`() {
        val differentYear = year1999
        val date = Date.YearOnly(differentYear)
        val formatted = formatterUs.format(date)
        assertThat(formatted).isEqualTo(YEAR_1999)
    }
}