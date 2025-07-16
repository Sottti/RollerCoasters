package com.sottti.roller.coasters.utils.time.dates.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.utils.time.dates.mapper.INVALID_DATE_FORMAT_MESSAGE
import com.sottti.roller.coasters.utils.time.dates.mapper.invalidFullDateMessage
import com.sottti.roller.coasters.utils.time.dates.mapper.invalidYearMonthMessage
import com.sottti.roller.coasters.utils.time.dates.mapper.toDate
import com.sottti.roller.coasters.utils.time.dates.mapper.toSortableString
import org.junit.Test
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
    fun `map to date full date with leading whitespace parses correctly`() {
        val result = whitespaceFullDateString.toDate()
        assertThat(result).isEqualTo(fullDate)
    }

    @Test
    fun `map to date leap year full date parses correctly`() {
        val result = leapYearFullDateString.toDate()
        assertThat(result).isEqualTo(leapYearFullDate)
    }

    @Test
    fun `map to date max valid year only parses correctly`() {
        val result = maxYearOnlyString.toDate()
        assertThat(result).isEqualTo(maxYearOnly)
    }

    @Test
    fun `map to date maximum full date parses correctly`() {
        val result = maxFullDateSortableString.toDate()
        assertThat(result).isEqualTo(maxFullDate)
    }

    @Test
    fun `map to date min valid year only parses correctly`() {
        val result = minYearOnlyString.toDate()
        assertThat(result).isEqualTo(minYearOnly)
    }

    @Test
    fun `map to date minimum full date parses correctly`() {
        val result = minFullDateSortableString.toDate()
        assertThat(result).isEqualTo(minFullDate)
    }

    @Test
    fun `map to date negative year parses correctly`() {
        val result = negativeYearSortableString.toDate()
        assertThat(result).isEqualTo(negativeYearOnly)
    }

    @Test
    fun `map to date year and month with single digit month parses correctly`() {
        val result = singleDigitMonthString.toDate()
        assertThat(result).isEqualTo(singleDigitMonth)
    }

    @Test
    fun `map to date year and month with trailing whitespace parses correctly`() {
        val result = whitespaceYearMonthString.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date year and month without suffix parses correctly`() {
        val result = noSuffixYearMonthString.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date year only with surrounding whitespace parses correctly`() {
        val result = whitespaceYearString.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date year only without suffix parses correctly`() {
        val result = noSuffixYearString.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date zero year parses correctly`() {
        val result = zeroYearSortableString.toDate()
        assertThat(result).isEqualTo(zeroYearOnly)
    }

    @Test
    fun `map to date empty string throws illegal argument exception`() {
        assertThat(emptyString.toDate()).isNull()
    }

    @Test
    fun `map to date extreme negative year throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            extremeNegativeYearSortableString.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(INVALID_DATE_FORMAT_MESSAGE.format(extremeNegativeYearSortableString))
    }

    @Test
    fun `map to date extreme positive year throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            extremePositiveYearSortableString.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(extremePositiveYearSortableString),
            )
    }

    @Test
    fun `map to date full date with invalid day throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            invalidDayFullDateString.toDate()
        }
        assertThat(exception.message).isEqualTo(invalidFullDateMessage(invalidDayFullDateString))
    }

    @Test
    fun `map to date full date with partial suffix throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            partialSuffixFullDateString.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(partialSuffixFullDateString)
            )
    }

    @Test
    fun `map to date internal whitespace throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            internalWhitespaceDate.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(internalWhitespaceDate)
            )
    }

    @Test
    fun `map to date invalid month in year-month throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            invalidYearMonthInvalidMonth.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(invalidYearMonthMessage(invalidYearMonthInvalidMonth))
    }

    @Test
    fun `map to date random text throws exception with correct message`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            randomText.toDate()
        }
        assertThat(exception.message).isEqualTo(INVALID_DATE_FORMAT_MESSAGE.format(randomText))
    }

    @Test
    fun `map to date year and month with invalid month parses as year only`() {
        val result = invalidMonthYearMonth.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date year and month with partial suffix throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            partialSuffixYearMonth.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(partialSuffixYearMonth)
            )
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

    @Test
    fun `sorting works correctly with mixed suffix and no-suffix dates`() {
        val result = unsortedMixedSuffixDates
            .sortedBy { it.toSortableString() }
            .map { it.toSortableString() }
        assertThat(result)
            .containsExactly(*sortedMixedSuffixDates)
            .inOrder()
    }
}