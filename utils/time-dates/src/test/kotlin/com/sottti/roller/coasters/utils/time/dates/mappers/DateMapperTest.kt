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
        assertThat(result).isEqualTo(FULL_DATE_SORTABLE_STRING)
    }

    @Test
    fun `year and month to sortable string returns correct format`() {
        val result = yearAndMonth.toSortableString()
        assertThat(result).isEqualTo(YEAR_AND_MONTH_SORTABLE_STRING)
    }

    @Test
    fun `year only to sortable string returns correct format`() {
        val result = yearOnly.toSortableString()
        assertThat(result).isEqualTo(YEAR_ONLY_SORTABLE_STRING)
    }

    @Test
    fun `map to date correctly parses full date`() {
        val result = FULL_DATE_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(fullDate)
    }

    @Test
    fun `map to date correctly parses year and month`() {
        val result = YEAR_AND_MONTH_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date correctly parses year only`() {
        val result = YEAR_ONLY_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date full date with leading whitespace parses correctly`() {
        val result = WHITESPACE_FULL_DATE_STRING.toDate()
        assertThat(result).isEqualTo(fullDate)
    }

    @Test
    fun `map to date leap year full date parses correctly`() {
        val result = LEAP_YEAR_FULL_DATE_STRING.toDate()
        assertThat(result).isEqualTo(leapYearFullDate)
    }

    @Test
    fun `map to date max valid year only parses correctly`() {
        val result = MAX_YEAR_ONLY_STRING.toDate()
        assertThat(result).isEqualTo(maxYearOnly)
    }

    @Test
    fun `map to date maximum full date parses correctly`() {
        val result = MAX_FULL_DATE_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(maxFullDate)
    }

    @Test
    fun `map to date min valid year only parses correctly`() {
        val result = MIN_YEAR_ONLY_STRING.toDate()
        assertThat(result).isEqualTo(minYearOnly)
    }

    @Test
    fun `map to date minimum full date parses correctly`() {
        val result = MIN_FULL_DATE_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(minFullDate)
    }

    @Test
    fun `map to date negative year parses correctly`() {
        val result = NEGATIVE_YEAR_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(negativeYearOnly)
    }

    @Test
    fun `map to date year and month with single digit month parses correctly`() {
        val result = SINGLE_DIGIT_MONTH_STRING.toDate()
        assertThat(result).isEqualTo(singleDigitMonth)
    }

    @Test
    fun `map to date year and month with trailing whitespace parses correctly`() {
        val result = WHITESPACE_YEAR_MONTH_STRING.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date year and month without suffix parses correctly`() {
        val result = NO_SUFFIX_YEAR_MONTH_STRING.toDate()
        assertThat(result).isEqualTo(yearAndMonth)
    }

    @Test
    fun `map to date year only with surrounding whitespace parses correctly`() {
        val result = WHITESPACE_YEAR_STRING.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date year only without suffix parses correctly`() {
        val result = NO_SUFFIX_YEAR_STRING.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date zero year parses correctly`() {
        val result = ZERO_YEAR_SORTABLE_STRING.toDate()
        assertThat(result).isEqualTo(zeroYearOnly)
    }

    @Test
    fun `map to date empty string throws illegal argument exception`() {
        assertThat(EMPTY_STRING.toDate()).isNull()
    }

    @Test
    fun `map to date extreme negative year throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            EXTREME_NEGATIVE_YEAR_SORTABLE_STRING.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(INVALID_DATE_FORMAT_MESSAGE.format(EXTREME_NEGATIVE_YEAR_SORTABLE_STRING))
    }

    @Test
    fun `map to date extreme positive year throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            EXTREME_POSITIVE_YEAR_SORTABLE_STRING.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(EXTREME_POSITIVE_YEAR_SORTABLE_STRING),
            )
    }

    @Test
    fun `map to date full date with invalid day throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            INVALID_DAY_FULL_DATE_STRING.toDate()
        }
        assertThat(exception.message).isEqualTo(invalidFullDateMessage(INVALID_DAY_FULL_DATE_STRING))
    }

    @Test
    fun `map to date full date with partial suffix throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            PARTIAL_SUFFIX_FULL_DATE_STRING.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(PARTIAL_SUFFIX_FULL_DATE_STRING)
            )
    }

    @Test
    fun `map to date internal whitespace throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            INTERNAL_WHITESPACE_DATE.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(INTERNAL_WHITESPACE_DATE)
            )
    }

    @Test
    fun `map to date invalid month in year-month throws illegal argument exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            INVALID_YEAR_MONTH_INVALID_MONTH.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(invalidYearMonthMessage(INVALID_YEAR_MONTH_INVALID_MONTH))
    }

    @Test
    fun `map to date random text throws exception with correct message`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            RANDOM_TEXT.toDate()
        }
        assertThat(exception.message).isEqualTo(INVALID_DATE_FORMAT_MESSAGE.format(RANDOM_TEXT))
    }

    @Test
    fun `map to date year and month with invalid month parses as year only`() {
        val result = INVALID_MONTH_YEAR_MONTH.toDate()
        assertThat(result).isEqualTo(yearOnly)
    }

    @Test
    fun `map to date year and month with partial suffix throws exception`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            PARTIAL_SUFFIX_YEAR_MONTH.toDate()
        }
        assertThat(exception.message)
            .isEqualTo(
                INVALID_DATE_FORMAT_MESSAGE.format(PARTIAL_SUFFIX_YEAR_MONTH)
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
