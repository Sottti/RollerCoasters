package com.sottti.roller.coasters.utils.device

import com.sottti.roller.coasters.domain.model.SystemColorContrast.HighContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.LowContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.model.SystemColorContrast.StandardContrast
import com.sottti.roller.coasters.utils.device.system.SystemSettings
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class ColorContrastTest {

    @Test
    fun `contrast below 0 returns low contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns -0.1f },
        )
        assertEquals(LowContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast below 0 returns standard contrast on sdk level 33`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns false },
            uiModeManager = mockk { every { contrast } returns -0.1f },
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 0_0 returns standard contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns 0.0f },
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 0_4 returns standard contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns 0.4f },
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 0_5 returns medium contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns 0.5f },
        )
        assertEquals(MediumContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 0_99 returns medium contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns 0.99f },
        )
        assertEquals(MediumContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 1_0 returns high contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = mockk { every { contrast } returns 1.0f },
        )
        assertEquals(HighContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast 1_0 returns high contrast on sdk level 33`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns false },
            uiModeManager = mockk { every { contrast } returns 1.0f },
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast null returns standard contrast on sdk level 34`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns true },
            uiModeManager = null,
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }

    @Test
    fun `contrast null returns standard contrast on sdk level 33`() {
        val systemSettings = SystemSettings(
            sdkFeatures = mockk { every { colorContrastAvailable() } returns false },
            uiModeManager = null,
        )
        assertEquals(StandardContrast, systemSettings.colorContrast)
    }
}