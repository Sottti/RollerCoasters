package com.sottti.roller.coasters.utils.device

import com.sottti.roller.coasters.utils.device.accesibility.DeviceAccessibility
import com.sottti.roller.coasters.utils.device.accesibility.DeviceColorContrast.*
import com.sottti.roller.coasters.utils.device.sdk.SdkLevel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class ColorContrastTest {
    private val sdkLevel33 = SdkLevel(33)
    private val sdkLevel34 = SdkLevel(34)

    @Test
    fun `contrast below 0 returns low contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns -0.1f },
        )
        assertEquals(LowContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast below 0 returns standard contrast on sdk level 33`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel33,
            uiModeManager = mockk { every { contrast } returns -0.1f },
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 0_0 returns standard contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns 0.0f },
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 0_4 returns standard contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns 0.4f },
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 0_5 returns medium contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns 0.5f },
        )
        assertEquals(MediumContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 0_99 returns medium contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns 0.99f },
        )
        assertEquals(MediumContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 1_0 returns high contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = mockk { every { contrast } returns 1.0f },
        )
        assertEquals(HighContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast 1_0 returns high contrast on sdk level 33`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel33,
            uiModeManager = mockk { every { contrast } returns 1.0f },
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast null returns standard contrast on sdk level 34`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel34,
            uiModeManager = null,
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }

    @Test
    fun `contrast null returns standard contrast on sdk level 33`() {
        val deviceAccessibility = DeviceAccessibility(
            sdkLevel = sdkLevel33,
            uiModeManager = null,
        )
        assertEquals(StandardContrast, deviceAccessibility.colorContrast)
    }
}
