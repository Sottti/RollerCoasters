package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.EnglishGb
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.SpanishSpain
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetAppLanguageTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getAppLanguage: GetAppLanguage

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getAppLanguage = GetAppLanguage(settingsRepository)
    }

    @Test
    fun `returns english when repository provides english`() = runTest {
        coEvery { settingsRepository.getAppLanguage() } returns EnglishGb
        val result = getAppLanguage()
        assertEquals(EnglishGb, result)
    }

    @Test
    fun `returns spanish when repository provides spanish`() = runTest {
        coEvery { settingsRepository.getAppLanguage() } returns SpanishSpain
        val result = getAppLanguage()
        assertEquals(SpanishSpain, result)
    }
}
