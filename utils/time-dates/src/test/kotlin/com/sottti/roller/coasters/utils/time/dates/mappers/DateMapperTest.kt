package com.sottti.roller.coasters.utils.time.dates.mappers

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.format.DateTimeParseException
import kotlin.test.assertFailsWith

internal class DateMapperTest {

    @Test
    fun `full date to sortable string returns correct format`() {
        val result = fullDate.toSortableString()
        assertThat(result).isEqualTo(fullDateSortableString)
    }

    @Test
    fun `year and month to sortable string returns correct format`() {
        val result = yearAndMonth.toSortableString()
        assertThat(result).isEqualTo(yearAndMonthSortableString)
    }

    @Test
    fun `year only to sortable string returns correct format`() {
        val result = yearOnly.toSortableString()
        assertThat(result).isEqualTo(yearOnlySortableString)
    }

    @Test
    fun `map to date correctly parses full date`() {
        val result = fullDateSortableString.toDate()
        assertThat(result).isEqualTo(fullDate)
    }

    @Test
    fun `map to date correctly parses year and month`() {
        val result = yearAndMonthSortableString.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date correctly parses year only`() {
        val result = yearOnlySortableString.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map empty string to date returns null`() {
        val result = "".toDate()
        assertThat(result).isNull()
    }

    @Test
    fun `invalid date format throws exception`() {
        assertFailsWith<DateTimeParseException> {
            invalidDate.toDate()
        }
    }

    @Test
    fun `random text throws exception`() {
        assertFailsWith<IllegalArgumentException> {
            randomText.toDate()
        }
    }

    @Test
    fun `sorting works correctly`() {
        val result = unsortedMixedDates
            .sortedBy { it.toSortableString() }
            .map { it.toSortableString() }

        assertThat(result)
            .containsExactly(*sortedMixedDates)
            .inOrder()
    }
}