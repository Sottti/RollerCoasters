package com.sottti.roller.coasters.data.settings.mappers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Theme
import org.junit.Test

internal class ThemeMapperTest {

    @Test
    fun `dark theme maps to ui mode manager MODE_NIGHT_YES`() {
        assertThat(Theme.DarkTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to ui mode manager MODE_NIGHT_NO`() {
        assertThat(Theme.LightTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to ui mode manager MODE_NIGHT_CUSTOM`() {
        assertThat(Theme.SystemTheme.toUiModeManagerNightMode())
            .isEqualTo(UiModeManager.MODE_NIGHT_CUSTOM)
    }

    @Test
    fun `dark theme maps to app compat delegate MODE_NIGHT_YES`() {
        assertThat(Theme.DarkTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to app compat delegate MODE_NIGHT_NO`() {
        assertThat(Theme.LightTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to app compat delegate MODE_NIGHT_FOLLOW_SYSTEM`() {
        assertThat(Theme.SystemTheme.toAppCompatDelegateNightMode())
            .isEqualTo(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    @Test
    fun `dark theme maps to correct key`() {
        assertThat(Theme.DarkTheme.key)
            .isEqualTo(THEME_KEY_DARK)
    }

    @Test
    fun `light theme maps to correct key`() {
        assertThat(Theme.LightTheme.key)
            .isEqualTo(THEME_KEY_LIGHT)
    }

    @Test
    fun `system theme maps to correct key`() {
        assertThat(Theme.SystemTheme.key)
            .isEqualTo(THEME_KEY_SYSTEM)
    }
}