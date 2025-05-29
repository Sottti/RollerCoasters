package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveAppThemeTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeAppTheme: ObserveAppTheme

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeAppTheme = ObserveAppTheme(settingsRepository)
    }

    @Test
    fun `emits light app theme when repository observes light app theme`() = runTest {
        every { settingsRepository.observeAppTheme() } returns flowOf(LightAppTheme)
        val emissions = observeAppTheme().toList()
        assertThat(emissions).containsExactly(LightAppTheme)
    }

    @Test
    fun `emits dark app theme when repository observes dark app theme`() = runTest {
        every { settingsRepository.observeAppTheme() } returns flowOf(DarkAppTheme)
        val emissions = observeAppTheme().toList()
        assertThat(emissions).containsExactly(DarkAppTheme)
    }

    @Test
    fun `emits system app theme when repository observes system app theme`() = runTest {
        every { settingsRepository.observeAppTheme() } returns flowOf(System)
        val emissions = observeAppTheme().toList()
        assertThat(emissions).containsExactly(System)
    }

    @Test
    fun `emits multiple values when repository observes multiple themes`() = runTest {
        every { settingsRepository.observeAppTheme() } returns flowOf(LightAppTheme, DarkAppTheme)
        val emissions = observeAppTheme().toList()
        assertThat(emissions).containsExactly(LightAppTheme, DarkAppTheme)
    }
}