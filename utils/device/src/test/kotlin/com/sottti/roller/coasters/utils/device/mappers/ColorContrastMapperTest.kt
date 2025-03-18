package com.sottti.roller.coasters.utils.device.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.SystemColorContrast.HighContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.LowContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.StandardContrast
import org.junit.Test

internal class ColorContrastMapperTest {

    @Test
    fun `should return low when contrast is at lower edge -1`() {
        val result = toSystemColorContrast(-1.0f)
        assertThat(result).isEqualTo(LowContrast)
    }

    @Test
    fun `should return low when contrast is negative -0_1`() {
        val result = toSystemColorContrast(-0.1f)
        assertThat(result).isEqualTo(LowContrast)
    }

    @Test
    fun `should return standard when contrast is zero`() {
        val result = toSystemColorContrast(0f)
        assertThat(result).isEqualTo(StandardContrast)
    }

    @Test
    fun `should return standard when contrast is just below 0_5`() {
        val result = toSystemColorContrast(0.49f)
        assertThat(result).isEqualTo(StandardContrast)
    }

    @Test
    fun `should return medium when contrast is exactly 0_5`() {
        val result = toSystemColorContrast(0.5f)
        assertThat(result).isEqualTo(MediumContrast)
    }

    @Test
    fun `should return medium when contrast is just below 1`() {
        val result = toSystemColorContrast(0.99f)
        assertThat(result).isEqualTo(MediumContrast)
    }

    @Test
    fun `should return high when contrast is exactly 1`() {
        val result = toSystemColorContrast(1.0f)
        assertThat(result).isEqualTo(HighContrast)
    }

    @Test
    fun `should return high when contrast is above 1`() {
        val result = toSystemColorContrast(1.1f)
        assertThat(result).isEqualTo(HighContrast)
    }

    @Test
    fun `should return low when contrast is below valid range -2`() {
        val result = toSystemColorContrast(-2.0f)
        assertThat(result).isEqualTo(LowContrast)
    }

    @Test
    fun `should return high when contrast is above valid range 2`() {
        val result = toSystemColorContrast(2.0f)
        assertThat(result).isEqualTo(HighContrast)
    }
}