package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SetAppColorContrastTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var setAppColorContrast: SetAppColorContrast

    @Before
    fun setUp() {
        settingsRepository = mockk()
        setAppColorContrast = SetAppColorContrast(settingsRepository)
    }

    @Test
    fun `sets high contrast in repository when invoked with high contrast`() = runTest {
        coEvery { settingsRepository.setAppColorContrast(HighContrast) } just runs
        setAppColorContrast(HighContrast)
        coVerify { settingsRepository.setAppColorContrast(HighContrast) }
    }

    @Test
    fun `sets medium contrast in repository when invoked with medium contrast`() = runTest {
        coEvery { settingsRepository.setAppColorContrast(MediumContrast) } just runs
        setAppColorContrast(MediumContrast)
        coVerify { settingsRepository.setAppColorContrast(MediumContrast) }
    }

    @Test
    fun `sets standard contrast in repository when invoked with standard contrast`() = runTest {
        coEvery { settingsRepository.setAppColorContrast(StandardContrast) } just runs
        setAppColorContrast(StandardContrast)
        coVerify { settingsRepository.setAppColorContrast(StandardContrast) }
    }

    @Test
    fun `sets system contrast in repository when invoked with system contrast`() = runTest {
        coEvery { settingsRepository.setAppColorContrast(System) } just runs
        setAppColorContrast(System)
        coVerify { settingsRepository.setAppColorContrast(System) }
    }
}