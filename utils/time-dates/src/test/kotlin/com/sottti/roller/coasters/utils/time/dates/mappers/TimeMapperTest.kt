package com.sottti.roller.coasters.utils.time.dates.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.utils.time.dates.mapper.toSeconds
import org.junit.Test
import kotlin.test.assertFailsWith

internal class TimeMapperTest {

    @Test
    fun `map to seconds converts 02_30 to 150 seconds`() {
        val result = "02:30".toSeconds()
        assertThat(result).isEqualTo(150)
    }

    @Test
    fun `map to seconds converts 00_00 to 0 seconds`() {
        val result = "00:00".toSeconds()
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `map to seconds converts 59_59 to 3599 seconds`() {
        val result = "59:59".toSeconds()
        assertThat(result).isEqualTo(3599)
    }

    @Test
    fun `map to seconds converts 01_00 to 60 seconds`() {
        val result = "01:00".toSeconds()
        assertThat(result).isEqualTo(60)
    }

    @Test
    fun `map to seconds greater than 59 rolls over to minutes`() {
        val result = "10:61".toSeconds()
        assertThat(result).isEqualTo(661)
    }

    @Test
    fun `invalid format without colon throws exception`() {
        assertFailsWith<Exception> {
            "5".toSeconds()
        }
    }

    @Test
    fun `invalid non-numeric input throws exception`() {
        assertFailsWith<NumberFormatException> {
            "abc:30".toSeconds()
        }
    }

    @Test
    fun `empty string throws exception`() {
        assertFailsWith<Exception> {
            "".toSeconds()
        }
    }
}