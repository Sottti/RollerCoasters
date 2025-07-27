package com.sottti.roller.coasters.domain.settings.usecase.language

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveAppLanguageTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeAppLanguage: ObserveAppLanguage

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeAppLanguage = ObserveAppLanguage(settingsRepository)
    }

    @Test
    fun `invoke returns flow from repository`() = runTest {
        val expectedLanguage = AppLanguage.SpanishSpain
        every { settingsRepository.observeAppLanguage() } returns flowOf(expectedLanguage)

        val result = observeAppLanguage().toList()
        assertThat(result).containsExactly(expectedLanguage).inOrder()
    }

    @Test
    fun `invoke emits multiple values from repository`() = runTest {
        val languages = listOf(AppLanguage.System, AppLanguage.Galician, AppLanguage.EnglishGb)
        every { settingsRepository.observeAppLanguage() } returns flowOf(*languages.toTypedArray())

        val result = observeAppLanguage().toList()
        assertThat(result).containsExactlyElementsIn(languages).inOrder()
    }

    @Test
    fun `invoke delegates to repository without modification`() = runTest {
        val mockFlow = flowOf(AppLanguage.System)
        every { settingsRepository.observeAppLanguage() } returns mockFlow

        val result = observeAppLanguage().toList()
        assertThat(result).containsExactly(AppLanguage.System).inOrder()
    }
}
