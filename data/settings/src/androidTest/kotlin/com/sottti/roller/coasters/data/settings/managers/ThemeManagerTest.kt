package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
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
    private lateinit var features: SystemFeatures
    private lateinit var manager: ThemeManager
    private lateinit var uiModeManager: UiModeManager

    @Before
    fun setup() {
        context = mockk()
        features = mockk()
        uiModeManager = mockk()
        manager = ThemeManager(context, features, uiModeManager)
        mockkStatic(AppCompatDelegate::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(AppCompatDelegate::class)
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailable() {
        every { features.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) } just runs

        manager.setTheme(AppTheme.DarkAppTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureAvailable() {
        every { features.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) } just runs

        manager.setTheme(AppTheme.LightAppTheme)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureAvailable() {
        every { features.setPersistentNightModeAvailable() } returns true
        every { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_CUSTOM) } just runs

        manager.setTheme(AppTheme.System)

        verify { uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_CUSTOM) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureAvailableAndUiModeManagerNull() {
        every { features.setPersistentNightModeAvailable() } returns true
        manager = ThemeManager(context, features, null)

        manager.setTheme(AppTheme.DarkAppTheme)

        verify(exactly = 0) { AppCompatDelegate.setDefaultNightMode(any()) }
    }

    @Test
    fun testSetThemeDarkWhenFeatureUnavailable() {
        every { features.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) } just runs

        manager.setTheme(AppTheme.DarkAppTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
    }

    @Test
    fun testSetThemeLightWhenFeatureUnavailable() {
        every { features.setPersistentNightModeAvailable() } returns false
        every { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) } just runs

        manager.setTheme(AppTheme.LightAppTheme)

        verify { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
    }

    @Test
    fun testSetThemeSystemWhenFeatureUnavailable() {
        every { features.setPersistentNightModeAvailable() } returns false
        every {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } just runs

        manager.setTheme(AppTheme.System)

        verify {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
