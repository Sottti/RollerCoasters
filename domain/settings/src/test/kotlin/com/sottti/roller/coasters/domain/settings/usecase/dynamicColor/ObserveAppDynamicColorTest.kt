package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Disabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Enabled
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveAppDynamicColorTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeAppDynamicColor: ObserveAppDynamicColor

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeAppDynamicColor = ObserveAppDynamicColor(settingsRepository)
    }

    @Test
    fun `emits enabled when repository observes enabled`() = runTest {
        every { settingsRepository.observeAppDynamicColor() } returns flowOf(Enabled)
        val emissions = observeAppDynamicColor().toList()
        assertThat(emissions).containsExactly(Enabled)
    }

    @Test
    fun `emits disabled when repository observes disabled`() = runTest {
        every { settingsRepository.observeAppDynamicColor() } returns flowOf(Disabled)
        val emissions = observeAppDynamicColor().toList()
        assertThat(emissions).containsExactly(Disabled)
    }

    @Test
    fun `emits multiple values when repository observes multiple dynamic colors`() = runTest {
        every {
            settingsRepository.observeAppDynamicColor()
        } returns flowOf(Enabled, Disabled)
        val emissions = observeAppDynamicColor().toList()
        assertThat(emissions).containsExactly(Enabled, Disabled)
    }
}
