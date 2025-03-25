package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.COLOR_CONTRAST_KEY_HIGH
import com.sottti.roller.coasters.data.settings.mapper.COLOR_CONTRAST_KEY_MEDIUM
import com.sottti.roller.coasters.data.settings.mapper.COLOR_CONTRAST_KEY_STANDARD
import com.sottti.roller.coasters.data.settings.mapper.COLOR_CONTRAST_KEY_SYSTEM
import com.sottti.roller.coasters.data.settings.mapper.key
import com.sottti.roller.coasters.data.settings.mapper.toAppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import org.junit.Test

internal class ColorContrastMapperTest {

    @Test
    fun `high contrast maps to its key`() {
        assertThat(AppColorContrast.HighContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_HIGH)
    }

    @Test
    fun `medium contrast maps to its key`() {
        assertThat(AppColorContrast.MediumContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_MEDIUM)
    }

    @Test
    fun `standard contrast maps to its key`() {
        assertThat(AppColorContrast.StandardContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_STANDARD)
    }

    @Test
    fun `system contrast maps to its key`() {
        assertThat(AppColorContrast.SystemContrast.key)
            .isEqualTo(COLOR_CONTRAST_KEY_SYSTEM)
    }

    @Test
    fun `high contrast key maps to high contrast`() {
        assertThat(COLOR_CONTRAST_KEY_HIGH.toAppColorContrast())
            .isEqualTo(AppColorContrast.HighContrast)
    }

    @Test
    fun `medium contrast key maps to medium contrast`() {
        assertThat(COLOR_CONTRAST_KEY_MEDIUM.toAppColorContrast())
            .isEqualTo(AppColorContrast.MediumContrast)
    }

    @Test
    fun `standard contrast key maps to standard contrast`() {
        assertThat(COLOR_CONTRAST_KEY_STANDARD.toAppColorContrast())
            .isEqualTo(AppColorContrast.StandardContrast)
    }

    @Test
    fun `system contrast key maps to system contrast`() {
        assertThat(COLOR_CONTRAST_KEY_SYSTEM.toAppColorContrast())
            .isEqualTo(AppColorContrast.SystemContrast)
    }

    @Test
    fun `unknown key maps to system contrast`() {
        assertThat("unknown_contrast".toAppColorContrast())
            .isEqualTo(AppColorContrast.SystemContrast)
    }

    @Test
    fun `empty key maps to system contrast`() {
        assertThat("".toAppColorContrast())
            .isEqualTo(AppColorContrast.SystemContrast)
    }
}