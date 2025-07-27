package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ApplyStoredAppThemeTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var applyStoredAppTheme: ApplyStoredAppTheme

    @Before
    fun setUp() {
        settingsRepository = mockk()
        applyStoredAppTheme = ApplyStoredAppTheme(settingsRepository)
    }

    @Test
    fun `applies stored app theme in repository when invoked`() = runTest {
        coEvery { settingsRepository.applyStoredAppTheme() } returns Unit
        applyStoredAppTheme()
        coVerify { settingsRepository.applyStoredAppTheme() }
    }
}
