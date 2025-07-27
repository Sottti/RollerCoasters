package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth
import com.sottti.roller.coasters.data.settings.mapper.toSystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import org.junit.Test

internal class SystemColorContrastMapperTest {

    @Test
    fun `should return low when contrast is at lower edge -1`() {
        val result = toSystemColorContrast(-1.0f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun `should return low when contrast is negative -0_1`() {
        val result = toSystemColorContrast(-0.1f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun `should return standard when contrast is zero`() {
        val result = toSystemColorContrast(0f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun `should return standard when contrast is just below 0_5`() {
        val result = toSystemColorContrast(0.49f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun `should return medium when contrast is exactly 0_5`() {
        val result = toSystemColorContrast(0.5f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun `should return medium when contrast is just below 1`() {
        val result = toSystemColorContrast(0.99f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun `should return high when contrast is exactly 1`() {
        val result = toSystemColorContrast(1.0f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun `should return high when contrast is above 1`() {
        val result = toSystemColorContrast(1.1f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun `should return low when contrast is below valid range -2`() {
        val result = toSystemColorContrast(-2.0f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun `should return high when contrast is above valid range 2`() {
        val result = toSystemColorContrast(2.0f)
        Truth.assertThat(result).isEqualTo(SystemColorContrast.HighContrast)
    }
}
