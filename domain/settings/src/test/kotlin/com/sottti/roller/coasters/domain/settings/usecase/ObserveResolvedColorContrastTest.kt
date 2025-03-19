package com.sottti.roller.coasters.domain.settings.usecase

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
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
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.HighContrast)

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.HighContrast)
    }

    @Test
    fun `emits resolved medium contrast when app medium contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.MediumContrast)

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.MediumContrast)
    }

    @Test
    fun `emits resolved standard contrast when app standard contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.StandardContrast)

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.StandardContrast)
    }

    @Test
    fun `emits resolved high contrast when system high contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.SystemContrast)

        coEvery {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.HighContrast

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.HighContrast)
    }

    @Test
    fun `emits resolved low contrast when system low contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.SystemContrast)

        coEvery {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.LowContrast

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.LowContrast)
    }

    @Test
    fun `emits resolved medium contrast when system medium contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.SystemContrast)

        coEvery {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.MediumContrast

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.MediumContrast)
    }

    @Test
    fun `emits resolved standard contrast when system standard contrast is observed`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(AppColorContrast.SystemContrast)

        coEvery {
            settingsRepository.getSystemColorContrast()
        } returns SystemColorContrast.StandardContrast

        val emissions = observeResolvedColorContrast().toList()

        assertThat(emissions).containsExactly(ResolvedColorContrast.StandardContrast)
    }
}