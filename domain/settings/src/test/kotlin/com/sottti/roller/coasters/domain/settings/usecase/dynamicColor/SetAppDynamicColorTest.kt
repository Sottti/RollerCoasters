package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Disabled
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor.Enabled
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SetAppDynamicColorTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var setAppDynamicColor: SetAppDynamicColor

    @Before
    fun setUp() {
        settingsRepository = mockk()
        setAppDynamicColor = SetAppDynamicColor(settingsRepository)
    }

    @Test
    fun `sets enabled in repository when invoked with enabled`() = runTest {
        coEvery { settingsRepository.setAppDynamicColor(Enabled) } just runs
        setAppDynamicColor(Enabled)
        coVerify { settingsRepository.setAppDynamicColor(Enabled) }
    }

    @Test
    fun `sets disabled in repository when invoked with disabled`() = runTest {
        coEvery { settingsRepository.setAppDynamicColor(Disabled) } just runs
        setAppDynamicColor(Disabled)
        coVerify { settingsRepository.setAppDynamicColor(Disabled) }
    }
}