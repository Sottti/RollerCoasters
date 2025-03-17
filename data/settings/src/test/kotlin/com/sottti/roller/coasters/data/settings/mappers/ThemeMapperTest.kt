package com.sottti.roller.coasters.data.settings.mappers

import android.app.UiModeManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Theme
import org.junit.Assume
import org.junit.Test

internal class ThemeMapperTest {

    @Test
    fun `dark theme maps to ui mode manager night mode yes`() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        assertThat(Theme.DarkTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to ui mode manager night mode no`() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        assertThat(Theme.LightTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to ui mode manager night mode custom`() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        assertThat(Theme.SystemTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_CUSTOM)
    }

    @Test
    fun `dark theme maps to app compat delegate night mode yes`() {
        assertThat(Theme.DarkTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to app compat delegate night mode no`() {
        assertThat(Theme.LightTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to app compat delegate night mode follow system`() {
        assertThat(Theme.SystemTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

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