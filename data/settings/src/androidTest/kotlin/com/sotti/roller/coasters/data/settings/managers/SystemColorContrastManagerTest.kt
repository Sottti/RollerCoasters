package com.sotti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sotti.roller.coasters.domain.system.features.SystemFeatures
import com.sotti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SystemColorContrastManagerTest {

    private lateinit var systemFeatures: SystemFeatures
    private lateinit var uiModeManager: UiModeManager
    private lateinit var manager: SystemColorContrastManager

    @Before
    fun setup() {
        systemFeatures = mockk()
        uiModeManager = mockk()
        manager = SystemColorContrastManager(systemFeatures, uiModeManager)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndLowContrast() {
        every { systemFeatures.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns -0.5f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndStandardContrast() {
        every { systemFeatures.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.2f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndMediumContrast() {
        every { systemFeatures.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.7f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndHighContrast() {
        every { systemFeatures.systemColorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 1.0f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndUiModeManagerNull() {
        every { systemFeatures.systemColorContrastAvailable() } returns true
        manager = SystemColorContrastManager(systemFeatures, null)

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() {
        every { systemFeatures.systemColorContrastAvailable() } returns false

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }
}
