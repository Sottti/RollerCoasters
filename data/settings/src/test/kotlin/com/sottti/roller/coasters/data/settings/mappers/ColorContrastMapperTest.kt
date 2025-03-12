package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.ColorContrast
import org.junit.Test

internal class ColorContrastMapperTest {

    @Test
    fun `high contrast maps to correct key`() {
        assertThat(ColorContrast.HighContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_HIGH)
    }

    @Test
    fun `medium contrast maps to correct key`() {
        assertThat(ColorContrast.MediumContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_MEDIUM)
    }

    @Test
    fun `standard contrast maps to correct key`() {
        assertThat(ColorContrast.StandardContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_STANDARD)
    }

    @Test
    fun `system contrast maps to correct key`() {
        assertThat(ColorContrast.SystemContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_SYSTEM)
    }
}