package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.EnglishGb
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.SpanishSpain
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SetAppLanguageTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var setAppLanguage: SetAppLanguage

    @Before
    fun setUp() {
        settingsRepository = mockk()
        setAppLanguage = SetAppLanguage(settingsRepository)
    }

    @Test
    fun `sets english in repository when invoked with english`() = runTest {
        coEvery { settingsRepository.setAppLanguage(EnglishGb) } returns Unit
        setAppLanguage(EnglishGb)
        coVerify { settingsRepository.setAppLanguage(EnglishGb) }
    }

    @Test
    fun `sets spanish in repository when invoked with spanish`() = runTest {
        coEvery { settingsRepository.setAppLanguage(SpanishSpain) } returns Unit
        setAppLanguage(SpanishSpain)
        coVerify { settingsRepository.setAppLanguage(SpanishSpain) }
    }
}