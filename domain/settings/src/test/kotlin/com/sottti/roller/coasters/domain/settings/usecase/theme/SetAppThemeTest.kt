package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.SystemAppTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SetAppThemeTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var setAppTheme: SetAppTheme

    @Before
    fun setUp() {
        settingsRepository = mockk()
        setAppTheme = SetAppTheme(settingsRepository)
    }

    @Test
    fun `sets light app theme in repository when invoked with light app theme`() = runTest {
        coEvery { settingsRepository.setAppTheme(LightAppTheme) } returns Unit
        setAppTheme(LightAppTheme)
        coVerify { settingsRepository.setAppTheme(LightAppTheme) }
    }

    @Test
    fun `sets dark app theme in repository when invoked with dark app theme`() = runTest {
        coEvery { settingsRepository.setAppTheme(DarkAppTheme) } returns Unit
        setAppTheme(DarkAppTheme)
        coVerify { settingsRepository.setAppTheme(DarkAppTheme) }
    }

    @Test
    fun `sets system app theme in repository when invoked with system app theme`() = runTest {
        coEvery { settingsRepository.setAppTheme(SystemAppTheme) } returns Unit
        setAppTheme(SystemAppTheme)
        coVerify { settingsRepository.setAppTheme(SystemAppTheme) }
    }
}