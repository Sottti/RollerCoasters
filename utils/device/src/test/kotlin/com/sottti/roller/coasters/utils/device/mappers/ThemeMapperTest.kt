package com.sottti.roller.coasters.utils.device.mappers

import android.app.UiModeManager.MODE_NIGHT_CUSTOM
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Theme
import org.junit.Test

internal class ThemeMappersTest {

    @Test
    fun `dark theme maps to night yes`() {
        val result = Theme.DarkTheme.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to night no`() {
        val result = Theme.LightTheme.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to night custom`() {
        val result = Theme.SystemTheme.toUiModeManagerNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_CUSTOM)
    }

    @Test
    fun `dark theme maps to app compat night yes`() {
        val result = Theme.DarkTheme.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `light theme maps to app compat night no`() {
        val result = Theme.LightTheme.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @Test
    fun `system theme maps to app compat follow system`() {
        val result = Theme.SystemTheme.toAppCompatDelegateNightMode()
        assertThat(result).isEqualTo(MODE_NIGHT_FOLLOW_SYSTEM)
    }
}