package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SystemColorContrastManagerTest {

    private lateinit var sdkFeatures: SdkFeatures
    private lateinit var uiModeManager: UiModeManager
    private lateinit var manager: SystemColorContrastManager

    @Before
    fun setup() {
        sdkFeatures = mockk()
        uiModeManager = mockk()
        manager = SystemColorContrastManager(sdkFeatures, uiModeManager)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndLowContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns -0.5f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndStandardContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.2f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndMediumContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.7f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndHighContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 1.0f

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndUiModeManagerNull() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        manager = SystemColorContrastManager(sdkFeatures, null)

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() {
        every { sdkFeatures.colorContrastAvailable() } returns false

        assertThat(manager.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }
}