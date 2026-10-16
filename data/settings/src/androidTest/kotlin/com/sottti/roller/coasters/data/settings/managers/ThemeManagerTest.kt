package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ThemeManagerTest {

    private lateinit var context: Context
    private lateinit var systemFeatures: SystemFeatures
    private lateinit var manager: ThemeManager
    private lateinit var uiModeManager: UiModeManager

    @Before
    fun setup() {
        context = mockk()
        systemFeatures = mockk()
        uiModeManager = mockk()
        manager = ThemeManager(context, systemFeatures, uiModeManager)
        mockkStatic(AppCompatDelegate::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(AppCompatDelegate::class)
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) } just runs

        manager.setTheme(AppTheme.DarkAppTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureAvailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) } just runs

        manager.setTheme(AppTheme.LightAppTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureAvailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO) } just runs

        manager.setTheme(AppTheme.System)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailableAndUiModeManagerNull() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns true
        manager = ThemeManager(context, systemFeatures, null)

        manager.setTheme(AppTheme.DarkAppTheme)

        verify(exactly = 0) { AppCompatDelegate.setDefaultNightMode(any()) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureUnavailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) } just runs

        manager.setTheme(AppTheme.DarkAppTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureUnavailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) } just runs

        manager.setTheme(AppTheme.LightAppTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureUnavailable() {
        every { systemFeatures.setPersistentNightModeAvailable() } returns false
        every {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } just runs

        manager.setTheme(AppTheme.System)

        verify {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
