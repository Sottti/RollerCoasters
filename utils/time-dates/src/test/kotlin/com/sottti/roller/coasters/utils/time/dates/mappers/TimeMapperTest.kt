package com.sottti.roller.coasters.utils.time.dates.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.utils.time.dates.mapper.toSeconds
import org.junit.Test
import kotlin.test.assertFailsWith

internal class TimeMapperTest {

    @Test
    fun `converts 02 colon 30 to 150 seconds`() {
        assertThat("02:30".toSeconds()).isEqualTo(150)
    }

    @Test
    fun `converts 00 colon 00 to 0 seconds`() {
        assertThat("00:00".toSeconds()).isEqualTo(0)
    }

    @Test
    fun `converts 59 colon 59 to 3599 seconds`() {
        assertThat("59:59".toSeconds()).isEqualTo(3599)
    }

    @Test
    fun `converts 01 colon 00 to 60 seconds`() {
        assertThat("01:00".toSeconds()).isEqualTo(60)
    }

    @Test
    fun `converts 10 colon 61 to 661 seconds`() {
        assertThat("10:61".toSeconds()).isEqualTo(661)
    }

    @Test
    fun `handles single digit minutes`() {
        assertThat("5:30".toSeconds()).isEqualTo(330)
    }

    @Test
    fun `handles whitespace`() {
        assertThat(" 01:30 ".toSeconds()).isEqualTo(90)
    }

    @Test
    fun `throws on missing colon`() {
        val exception = assertFailsWith<IllegalArgumentException> { "5".toSeconds() }
        assertThat(exception.message).contains("MM:SS")
    }

    @Test
    fun `throws on non-numeric input`() {
        assertFailsWith<NumberFormatException> { "abc:30".toSeconds() }
    }

    @Test
    fun `throws on empty string`() {
        val exception = assertFailsWith<IllegalArgumentException> { "".toSeconds() }
        assertThat(exception.message).contains("MM:SS")
    }

    @Test
    fun `throws on negative seconds`() {
        val exception = assertFailsWith<IllegalArgumentException> { "10:-5".toSeconds() }
        assertThat(exception.message).isEqualTo("Minutes and seconds must be non-negative")
    }

    @Test
    fun `throws on negative minutes`() {
        val exception = assertFailsWith<IllegalArgumentException> { "-1:30".toSeconds() }
        assertThat(exception.message).isEqualTo("Minutes and seconds must be non-negative")
    }

    @Test
    fun `throws on both negative`() {
        val exception = assertFailsWith<IllegalArgumentException> { "-1:-5".toSeconds() }
        assertThat(exception.message).isEqualTo("Minutes and seconds must be non-negative")
    }
}