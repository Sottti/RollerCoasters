package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetAppThemeTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getAppTheme: GetAppTheme

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getAppTheme = GetAppTheme(settingsRepository)
    }

    @Test
    fun `returns light app theme when repository provides light app theme`() = runTest {
        coEvery { settingsRepository.getAppTheme() } returns LightAppTheme
        val result = getAppTheme()
        assertEquals(LightAppTheme, result)
    }

    @Test
    fun `returns dark app theme when repository provides dark app theme`() = runTest {
        coEvery { settingsRepository.getAppTheme() } returns DarkAppTheme
        val result = getAppTheme()
        assertEquals(DarkAppTheme, result)
    }

    @Test
    fun `returns system app theme when repository provides system app theme`() = runTest {
        coEvery { settingsRepository.getAppTheme() } returns System
        val result = getAppTheme()
        assertEquals(System, result)
    }
}
