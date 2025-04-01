package com.sottti.roller.coasters.domain.settings.usecase.locale

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import java.util.Locale
import kotlin.test.Test

internal class GetDefaultLocaleTest {

    @Test
    fun `get default locale returns French France locale`() {
        val defaultLocale = Locale("fr", "FR")
        val settingsRepository = mockk<SettingsRepository>()
        every { settingsRepository.getDefaultLocale() } returns defaultLocale

        val useCase = GetDefaultLocale(settingsRepository)
        val result = useCase.invoke()

        assertThat(result).isEqualTo(defaultLocale)
    }

    @Test
    fun `get default locale returns English US locale`() {
        val defaultLocale = Locale("en", "US")
        val settingsRepository = mockk<SettingsRepository>()
        every { settingsRepository.getDefaultLocale() } returns defaultLocale

        val useCase = GetDefaultLocale(settingsRepository)
        val result = useCase.invoke()

        assertThat(result).isEqualTo(defaultLocale)
    }

    @Test
    fun `get default locale returns Spanish Spain locale`() {
        val defaultLocale = Locale("es", "ES")
        val settingsRepository = mockk<SettingsRepository>()
        every { settingsRepository.getDefaultLocale() } returns defaultLocale

        val useCase = GetDefaultLocale(settingsRepository)
        val result = useCase.invoke()

        assertThat(result).isEqualTo(defaultLocale)
    }

    @Test
    fun `get default locale handles empty language`() {
        val defaultLocale = Locale("", "US") // Empty language, valid country
        val settingsRepository = mockk<SettingsRepository>()
        every { settingsRepository.getDefaultLocale() } returns defaultLocale

        val useCase = GetDefaultLocale(settingsRepository)
        val result = useCase.invoke()

        assertThat(result).isEqualTo(defaultLocale)
        assertThat(result.language).isEmpty()
        assertThat(result.country).isEqualTo("US")
    }

    @Test
    fun `get default locale handles empty country`() {
        val defaultLocale = Locale("en", "") // Valid language, empty country
        val settingsRepository = mockk<SettingsRepository>()
        every { settingsRepository.getDefaultLocale() } returns defaultLocale

        val useCase = GetDefaultLocale(settingsRepository)
        val result = useCase.invoke()

        assertThat(result).isEqualTo(defaultLocale)
        assertThat(result.language).isEqualTo("en")
        assertThat(result.country).isEmpty()
    }
}