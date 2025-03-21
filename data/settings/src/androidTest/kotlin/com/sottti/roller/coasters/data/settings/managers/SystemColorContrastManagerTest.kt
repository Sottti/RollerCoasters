package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SystemColorContrastManagerTest {

    private lateinit var features: Features
    private lateinit var uiModeManager: UiModeManager
    private lateinit var manager: SystemColorContrastManager

    @Before
    fun setup() {
        features = mockk()
        uiModeManager = mockk()
        manager = SystemColorContrastManager(features, uiModeManager)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndLowContrast() {
        every { features.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns -0.5f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndStandardContrast() {
        every { features.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.2f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndMediumContrast() {
        every { features.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.7f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndHighContrast() {
        every { features.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 1.0f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndUiModeManagerNull() {
        every { features.systemColorContrastAvailable() } returns true
        manager = SystemColorContrastManager(features, null)

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() {
        every { features.systemColorContrastAvailable() } returns false

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }
}