package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Disabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Enabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class GetResolvedDynamicColorTest {

    private lateinit var systemFeatures: SystemFeatures
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getResolvedDynamicColor: GetResolvedDynamicColor

    @Before
    fun setUp() {
        systemFeatures = mockk()
        settingsRepository = mockk()
        getResolvedDynamicColor = GetResolvedDynamicColor(systemFeatures, settingsRepository)
    }

    @Test
    fun `returns resolved enabled when system supports dynamic color and app is enabled`() =
        runTest {
            every { systemFeatures.systemDynamicColorAvailable() } returns true
            coEvery { settingsRepository.getAppDynamicColor() } returns Enabled

            val result = getResolvedDynamicColor()

            assertThat(result).isEqualTo(ResolvedDynamicColor(enabled = true))
        }

    @Test
    fun `returns resolved disabled when system supports dynamic color and app is disabled`() =
        runTest {
            every { systemFeatures.systemDynamicColorAvailable() } returns true
            coEvery { settingsRepository.getAppDynamicColor() } returns Disabled

            val result = getResolvedDynamicColor()

            assertThat(result).isEqualTo(ResolvedDynamicColor(enabled = false))
        }

    @Test
    fun `returns disabled when system does not support dynamic color`() = runTest {
        every { systemFeatures.systemDynamicColorAvailable() } returns false

        val result = getResolvedDynamicColor()

        assertThat(result).isEqualTo(ResolvedDynamicColor(enabled = false))

        coVerify(exactly = 0) { settingsRepository.getAppDynamicColor() }
    }
}
