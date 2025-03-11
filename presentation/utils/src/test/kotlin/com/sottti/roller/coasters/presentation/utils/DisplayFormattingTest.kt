package com.sottti.roller.coasters.presentation.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DisplayFormatTest {

    @Test
    fun `Removes trailing zeros from decimal`() {
        val result = 123.4500.toDisplayFormat()
        assertThat(result).isEqualTo("123.45")
    }

    @Test
    fun `Removes unnecessary decimal point when only whole number remains`() {
        val result = 100.0.toDisplayFormat()
        assertThat(result).isEqualTo("100")
    }

    @Test
    fun `Keeps precision but removes trailing zeros`() {
        val result = 0.123456000.toDisplayFormat()
        assertThat(result).isEqualTo("0.123456")
    }

    @Test
    fun `Does not modify numbers without trailing zeros`() {
        val result = 999.999.toDisplayFormat()
        assertThat(result).isEqualTo("999.999")
    }

    @Test
    fun `Converts 0_0 to zero without decimal point`() {
        val result = 0.0.toDisplayFormat()
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun `Converts whole number with extra decimal zeros to integer`() {
        val result = 1.000000.toDisplayFormat()
        assertThat(result).isEqualTo("1")
    }

    @Test
    fun `Handles large numbers correctly`() {
        val result = 999999999999999.0000.toDisplayFormat()
        assertThat(result).isEqualTo("999999999999999")
    }

    @Test
    fun `Preserves negative sign while removing trailing zeros`() {
        val result = (-10.500).toDisplayFormat()
        assertThat(result).isEqualTo("-10.5")
    }

    @Test
    fun `Negative zero should become zero`() {
        val result = (-0.0).toDisplayFormat()
        assertThat(result).isEqualTo("0")
    }
}