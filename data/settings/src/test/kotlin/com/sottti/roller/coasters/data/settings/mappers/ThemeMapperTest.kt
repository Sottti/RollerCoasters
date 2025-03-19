package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import org.junit.Test

internal class ThemeMapperTest {

    @Test
    fun `dark theme maps to its key`() {
        assertThat(Theme.DarkTheme.key).isEqualTo(THEME_KEY_DARK)
    }

    @Test
    fun `light theme maps to its key`() {
        assertThat(Theme.LightTheme.key).isEqualTo(THEME_KEY_LIGHT)
    }

    @Test
    fun `system theme maps to its key`() {
        assertThat(Theme.SystemTheme.key).isEqualTo(THEME_KEY_SYSTEM)
    }

    @Test
    fun `dark key maps to dark theme`() {
        val result = THEME_KEY_DARK.toTheme()
        assertThat(result).isEqualTo(Theme.DarkTheme)
    }

    @Test
    fun `light key maps to light theme`() {
        val result = THEME_KEY_LIGHT.toTheme()
        assertThat(result).isEqualTo(Theme.LightTheme)
    }

    @Test
    fun `unknown key maps to system theme`() {
        val result = "unknown_theme".toTheme()
        assertThat(result).isEqualTo(Theme.SystemTheme)
    }

    @Test
    fun `empty key maps to system theme`() {
        val result = "".toTheme()
        assertThat(result).isEqualTo(Theme.SystemTheme)
    }
}