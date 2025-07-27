package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetAppColorContrastTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getAppColorContrast: GetAppColorContrast

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getAppColorContrast = GetAppColorContrast(settingsRepository)
    }

    @Test
    fun `returns high contrast when repository provides high contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns HighContrast
        val result = getAppColorContrast()
        assertEquals(HighContrast, result)
    }

    @Test
    fun `returns medium contrast when repository provides medium contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns MediumContrast
        val result = getAppColorContrast()
        assertEquals(MediumContrast, result)
    }

    @Test
    fun `returns standard contrast when repository provides standard contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns StandardContrast
        val result = getAppColorContrast()
        assertEquals(StandardContrast, result)
    }

    @Test
    fun `returns system contrast when repository provides system contrast`() = runTest {
        coEvery { settingsRepository.getAppColorContrast() } returns System
        val result = getAppColorContrast()
        assertEquals(System, result)
    }
}
