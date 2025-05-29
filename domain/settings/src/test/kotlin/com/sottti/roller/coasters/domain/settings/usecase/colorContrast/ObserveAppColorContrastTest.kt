package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class ObserveAppColorContrastTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeAppColorContrast: ObserveAppColorContrast

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeAppColorContrast = ObserveAppColorContrast(settingsRepository)
    }

    @Test
    fun `emits high contrast when repository flow emits high contrast`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(HighContrast)
        val result = observeAppColorContrast().toList()
        assertEquals(listOf(HighContrast), result)
    }

    @Test
    fun `emits medium contrast when repository flow emits medium contrast`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(MediumContrast)
        val result = observeAppColorContrast().toList()
        assertEquals(listOf(MediumContrast), result)
    }

    @Test
    fun `emits standard contrast when repository flow emits standard contrast`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(StandardContrast)
        val result = observeAppColorContrast().toList()
        assertEquals(listOf(StandardContrast), result)
    }

    @Test
    fun `emits system contrast when repository flow emits system contrast`() = runTest {
        every { settingsRepository.observeAppColorContrast() } returns flowOf(System)
        val result = observeAppColorContrast().toList()
        assertEquals(listOf(System), result)
    }

    @Test
    fun `emits multiple values when repository flow emits multiple contrasts`() = runTest {
        every {
            settingsRepository.observeAppColorContrast()
        } returns flowOf(HighContrast, MediumContrast)
        val result = observeAppColorContrast().toList()
        assertEquals(listOf(HighContrast, MediumContrast), result)
    }
}