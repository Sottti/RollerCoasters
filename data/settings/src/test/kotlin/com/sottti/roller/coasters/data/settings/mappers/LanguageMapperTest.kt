package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Language.*
import org.junit.Test
import java.util.Locale

internal class LanguageMapperTest {

    @Test
    fun `locale en-GB maps to English`() {
        val locale = Locale.UK
        assertThat(locale.toLanguage()).isEqualTo(EnglishGbLanguage)
    }

    @Test
    fun `locale gl-ES maps to Galician`() {
        val locale = Locale("gl", "ES")
        assertThat(locale.toLanguage()).isEqualTo(GalicianLanguage)
    }

    @Test
    fun `locale es-ES maps to Spanish`() {
        val locale = Locale("es", "ES")
        assertThat(locale.toLanguage()).isEqualTo(SpanishSpainLanguage)
    }

    @Test
    fun `unknown locale maps to system language`() {
        val locale = Locale("fr", "FR")
        assertThat(locale.toLanguage()).isEqualTo(SystemLanguage)
    }

    @Test
    fun `null locale maps to system language`() {
        val locale: Locale? = null
        assertThat(locale.toLanguage()).isEqualTo(SystemLanguage)
    }

    @Test
    fun `English converts correctly to locale list`() {
        val localeList = EnglishGbLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_UK_TAG)
    }

    @Test
    fun `Galician converts correctly to locale list`() {
        val localeList = GalicianLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
    }

    @Test
    fun `Spanish converts correctly to locale list`() {
        val localeList = SpanishSpainLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_SPAIN_TAG)
    }

    @Test
    fun `system language converts correctly to empty locale list`() {
        val localeList = SystemLanguage.toLocaleList()
        assertThat(localeList.isEmpty).isTrue()
    }
}