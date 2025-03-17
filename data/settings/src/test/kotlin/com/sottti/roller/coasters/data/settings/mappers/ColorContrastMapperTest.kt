package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.ColorContrast
import org.junit.Test

internal class ColorContrastMapperTest {

    @Test
    fun `high contrast maps to its key`() {
        assertThat(ColorContrast.HighContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_HIGH)
    }

    @Test
    fun `medium contrast maps to its key`() {
        assertThat(ColorContrast.MediumContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_MEDIUM)
    }

    @Test
    fun `standard contrast maps to its key`() {
        assertThat(ColorContrast.StandardContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_STANDARD)
    }

    @Test
    fun `system contrast maps to its key`() {
        assertThat(ColorContrast.SystemContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_SYSTEM)
    }

    @Test
    fun `high contrast key maps to high contrast`() {
        assertThat(COLOR_CONTRAST_KEY_HIGH.toColorContrast())
            .isEqualTo(ColorContrast.HighContrast)
    }

    @Test
    fun `medium contrast key maps to medium contrast`() {
        assertThat(COLOR_CONTRAST_KEY_MEDIUM.toColorContrast())
            .isEqualTo(ColorContrast.MediumContrast)
    }

    @Test
    fun `standard contrast key maps to standard contrast`() {
        assertThat(COLOR_CONTRAST_KEY_STANDARD.toColorContrast())
            .isEqualTo(ColorContrast.StandardContrast)
    }

    @Test
    fun `system contrast key maps to system contrast`() {
        assertThat(COLOR_CONTRAST_KEY_SYSTEM.toColorContrast())
            .isEqualTo(ColorContrast.SystemContrast)
    }

    @Test
    fun `unknown key maps to system contrast`() {
        assertThat("unknown_contrast".toColorContrast())
            .isEqualTo(ColorContrast.SystemContrast)
    }

    @Test
    fun `empty key maps to system contrast`() {
        assertThat("".toColorContrast())
            .isEqualTo(ColorContrast.SystemContrast)
    }
}