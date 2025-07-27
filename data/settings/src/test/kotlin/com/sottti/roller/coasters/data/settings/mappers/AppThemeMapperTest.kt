package com.sottti.roller.coasters.data.settings.mappers

import android.app.UiModeManager.MODE_NIGHT_CUSTOM
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.THEME_KEY_DARK
import com.sottti.roller.coasters.data.settings.mapper.THEME_KEY_LIGHT
import com.sottti.roller.coasters.data.settings.mapper.THEME_KEY_SYSTEM
import com.sottti.roller.coasters.data.settings.mapper.key
import com.sottti.roller.coasters.data.settings.mapper.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mapper.toTheme
import com.sottti.roller.coasters.data.settings.mapper.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import org.junit.Test

internal class AppThemeMapperTest {

    @Test
    fun `dark theme maps to its key`() {
        assertThat(AppTheme.DarkAppTheme.key).isEqualTo(THEME_KEY_DARK)
    }

    @Test
    fun `light theme maps to its key`() {
        assertThat(AppTheme.LightAppTheme.key).isEqualTo(THEME_KEY_LIGHT)
    }

    @Test
    fun `system theme maps to its key`() {
        assertThat(AppTheme.System.key).isEqualTo(THEME_KEY_SYSTEM)
    }

    @Test
    fun `dark key maps to dark theme`() {
        val result = THEME_KEY_DARK.toTheme()
        assertThat(result).isEqualTo(AppTheme.DarkAppTheme)
    }

    @Test
    fun `light key maps to light theme`() {
        val result = THEME_KEY_LIGHT.toTheme()
        assertThat(result).isEqualTo(AppTheme.LightAppTheme)
    }

    @Test
    fun `unknown key maps to system theme`() {
        val result = "unknown_theme".toTheme()
        assertThat(result).isEqualTo(AppTheme.System)
    }

    @Test
    fun `empty key maps to system theme`() {
        val result = "".toTheme()
        assertThat(result).isEqualTo(AppTheme.System)
    }

    @Test
    fun `dark theme maps to night yes`() {
        val result = AppTheme.DarkAppTheme.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to night no`() {
        val result = AppTheme.LightAppTheme.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to night custom`() {
        val result = AppTheme.System.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_CUSTOM)
    }

    @Test
    fun `dark theme maps to app compat night yes`() {
        val result = AppTheme.DarkAppTheme.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to app compat night no`() {
        val result = AppTheme.LightAppTheme.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to app compat follow system`() {
        val result = AppTheme.System.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
