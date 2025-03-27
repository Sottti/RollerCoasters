package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Disabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Enabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveResolvedDynamicColorTest {

    private lateinit var features: Features
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeResolvedDynamicColor: ObserveResolvedDynamicColor

    @Before
    fun setUp() {
        features = mockk()
        settingsRepository = mockk()
        observeResolvedDynamicColor = ObserveResolvedDynamicColor(features, settingsRepository)
    }

    @Test
    fun `emits resolved enabled when system supports dynamic color and app is enabled`() = runTest {
        every { features.systemDynamicColorAvailable() } returns true
        every { settingsRepository.observeAppDynamicColor() } returns flowOf(Enabled)
        val emissions = observeResolvedDynamicColor().toList()
        assertThat(emissions).containsExactly(ResolvedDynamicColor(enabled = true))
    }

    @Test
    fun `emits resolved disabled when system supports dynamic color and app is disabled`() =
        runTest {
            every { features.systemDynamicColorAvailable() } returns true
            every { settingsRepository.observeAppDynamicColor() } returns flowOf(Disabled)
            val emissions = observeResolvedDynamicColor().toList()
            assertThat(emissions).containsExactly(ResolvedDynamicColor(enabled = false))
        }

    @Test
    fun `emits disabled when system does not support dynamic color`() = runTest {
        every { features.systemDynamicColorAvailable() } returns false
        val emissions = observeResolvedDynamicColor().toList()
        assertThat(emissions).containsExactly(ResolvedDynamicColor(enabled = false))
    }

    @Test
    fun `emits multiple resolved values when system supports dynamic color and app changes`() =
        runTest {
            every { features.systemDynamicColorAvailable() } returns true
            every {
                settingsRepository.observeAppDynamicColor()
            } returns flowOf(Enabled, Disabled)
            val emissions = observeResolvedDynamicColor().toList()
            assertThat(emissions).containsExactly(
                ResolvedDynamicColor(enabled = true),
                ResolvedDynamicColor(enabled = false),
            )
        }
}