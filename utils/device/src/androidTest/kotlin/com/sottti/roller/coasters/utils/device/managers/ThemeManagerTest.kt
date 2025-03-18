package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
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

    private lateinit var sdkFeatures: SdkFeatures
    private lateinit var uiModeManager: UiModeManager
    private lateinit var manager: ThemeManager

    @Before
    fun setup() {
        sdkFeatures = mockk()
        uiModeManager = mockk()
        manager = ThemeManager(sdkFeatures, uiModeManager)
        mockkStatic(AppCompatDelegate::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(AppCompatDelegate::class)
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) } just runs

        manager.setTheme(Theme.DarkTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureAvailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) } just runs

        manager.setTheme(Theme.LightTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureAvailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_CUSTOM) } just runs

        manager.setTheme(Theme.SystemTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_CUSTOM) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailableAndUiModeManagerNull() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns true
        manager = ThemeManager(sdkFeatures, null)

        manager.setTheme(Theme.DarkTheme)

        verify(exactly = 0) { AppCompatDelegate.setDefaultNightMode(any()) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureUnavailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) } just runs

        manager.setTheme(Theme.DarkTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureUnavailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) } just runs

        manager.setTheme(Theme.LightTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureUnavailable() {
        every { sdkFeatures.setPersistentNightModeAvailable() } returns false
        every {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } just runs

        manager.setTheme(Theme.SystemTheme)

        verify {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}