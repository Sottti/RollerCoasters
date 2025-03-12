package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Language.EnglishGbLanguage
import com.sottti.roller.coasters.domain.model.Language.GalicianLanguage
import com.sottti.roller.coasters.domain.model.Language.SpanishSpainLanguage
import com.sottti.roller.coasters.domain.model.Language.SystemLanguage
import org.junit.Test
import java.util.Locale

internal class LanguageMapperTest {

    @Test
    fun `locale en-GB maps to english gb`() {
        val locale = Locale.UK
        assertThat(locale.toLanguage()).isEqualTo(EnglishGbLanguage)
    }

    @Test
    fun `locale gl-ES maps to galician`() {
        val locale = Locale("gl", "ES")
        assertThat(locale.toLanguage()).isEqualTo(GalicianLanguage)
    }

    @Test
    fun `locale es-ES maps to spanish from spain`() {
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
    fun `english gb converts correctly`() {
        val localeList = EnglishGbLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("en-GB")
    }

    @Test
    fun `galician converts correctly`() {
        val localeList = GalicianLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("gl-ES,es-ES")
    }

    @Test
    fun `spanish from spain converts correctly`() {
        val localeList = SpanishSpainLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("es-ES")
    }

    @Test
    fun `system language converts correctly`() {
        val localeList = SystemLanguage.toLocaleList()
        assertThat(localeList.isEmpty).isTrue()
    }
}