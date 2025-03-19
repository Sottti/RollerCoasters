package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.Language
import org.junit.Test
import java.util.Locale

internal class LocaleMapperTest {

    companion object {
        private const val UNMAPPED_LOCALE_TAG = "fr-FR"
    }

    @Test
    fun `locale en_gb maps to english gb`() {
        val locale = Locale.forLanguageTag(LOCALE_UK_TAG)
        assertThat(locale.toLanguage()).isEqualTo(Language.EnglishGbLanguage)
    }

    @Test
    fun `locale gl_es maps to galician`() {
        val locale = Locale.forLanguageTag(LOCALE_GALICIA_TAG)
        assertThat(locale.toLanguage()).isEqualTo(Language.GalicianLanguage)
    }

    @Test
    fun `locale es_es maps to spanish spain`() {
        val locale = Locale.forLanguageTag(LOCALE_SPAIN_TAG)
        assertThat(locale.toLanguage()).isEqualTo(Language.SpanishSpainLanguage)
    }

    @Test
    fun `unknown locale maps to system language`() {
        val locale = Locale.forLanguageTag(UNMAPPED_LOCALE_TAG)
        assertThat(locale.toLanguage()).isEqualTo(Language.SystemLanguage)
    }

    @Test
    fun `null locale maps to system language`() {
        val locale: Locale? = null
        assertThat(locale.toLanguage()).isEqualTo(Language.SystemLanguage)
    }

    @Test
    fun `english gb maps to locale list en_gb`() {
        val localeList = Language.EnglishGbLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_UK_TAG)
    }

    @Test
    fun `galician maps to locale list gl_es and es_es`() {
        val localeList = Language.GalicianLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
    }

    @Test
    fun `spanish spain maps to locale list es_es`() {
        val localeList = Language.SpanishSpainLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_SPAIN_TAG)
    }

    @Test
    fun `system language maps to empty locale list`() {
        val localeList = Language.SystemLanguage.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("")
    }
}