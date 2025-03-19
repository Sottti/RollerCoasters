package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.SystemColorContrast
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ColorContrastManagerTest {

    private lateinit var sdkFeatures: SdkFeatures
    private lateinit var uiModeManager: UiModeManager
    private lateinit var manager: ColorContrastManager

    @Before
    fun setup() {
        sdkFeatures = mockk()
        uiModeManager = mockk()
        manager = ColorContrastManager(sdkFeatures, uiModeManager)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndLowContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns -0.5f

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndStandardContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.2f

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndMediumContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 0.7f

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndHighContrast() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        every { uiModeManager.contrast } returns 1.0f

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testColorContrastWhenFeatureAvailableAndUiModeManagerNull() {
        every { sdkFeatures.colorContrastAvailable() } returns true
        manager = ColorContrastManager(sdkFeatures, null)

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() {
        every { sdkFeatures.colorContrastAvailable() } returns false

        assertThat(manager.colorContrast).isEqualTo(SystemColorContrast.StandardContrast)
    }
}