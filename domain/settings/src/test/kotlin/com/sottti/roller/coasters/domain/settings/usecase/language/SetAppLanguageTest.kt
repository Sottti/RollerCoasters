package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.EnglishGb
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.SpanishSpain
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
    fun `sets english in repository when invoked with english`() {
        every { settingsRepository.setAppLanguage(EnglishGb) } returns Unit
        setAppLanguage(EnglishGb)
        verify { settingsRepository.setAppLanguage(EnglishGb) }
    }

    @Test
    fun `sets spanish in repository when invoked with spanish`() {
        every { settingsRepository.setAppLanguage(SpanishSpain) } returns Unit
        setAppLanguage(SpanishSpain)
        verify { settingsRepository.setAppLanguage(SpanishSpain) }
    }
}