package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveResolvedColorContrastTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeResolvedColorContrast: ObserveResolvedColorContrast

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeResolvedColorContrast = ObserveResolvedColorContrast(settingsRepository)
    }

    @Test
    fun `emits resolved high contrast when app high contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(HighContrast)
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.HighContrast)
    }

    @Test
    fun `emits resolved medium contrast when app medium contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(MediumContrast)
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.MediumContrast)
    }

    @Test
    fun `emits resolved standard contrast when app standard contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(StandardContrast)
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.StandardContrast)
    }

    @Test
    fun `emits resolved high contrast when system high contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(System)
        coEvery {
            settingsRepository.getAppSystemColorContrast()
        } returns SystemColorContrast.HighContrast
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.HighContrast)
    }

    @Test
    fun `emits resolved low contrast when system low contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(System)
        coEvery {
            settingsRepository.getAppSystemColorContrast()
        } returns SystemColorContrast.LowContrast
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.LowContrast)
    }

    @Test
    fun `emits resolved medium contrast when system medium contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(System)
        coEvery {
            settingsRepository.getAppSystemColorContrast()
        } returns SystemColorContrast.MediumContrast
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.MediumContrast)
    }

    @Test
    fun `emits resolved standard contrast when system standard contrast is observed`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(System)
        coEvery {
            settingsRepository.getAppSystemColorContrast()
        } returns SystemColorContrast.StandardContrast
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(ResolvedColorContrast.StandardContrast)
    }

    @Test
    fun `emits multiple resolved contrasts when observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(HighContrast, System)
        coEvery {
            settingsRepository.getAppSystemColorContrast()
        } returns SystemColorContrast.MediumContrast
        val emissions = observeResolvedColorContrast().toList()
        assertThat(emissions).containsExactly(
            ResolvedColorContrast.HighContrast,
            ResolvedColorContrast.MediumContrast,
        )
    }
}