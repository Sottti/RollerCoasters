package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.LOCALE_GALICIA_TAG
import com.sottti.roller.coasters.data.settings.mapper.LOCALE_SPAIN_TAG
import com.sottti.roller.coasters.data.settings.mapper.LOCALE_UK_TAG
import com.sottti.roller.coasters.data.settings.mapper.toLanguage
import com.sottti.roller.coasters.data.settings.mapper.toLocaleList
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import org.junit.Test
import java.util.Locale

internal class LocaleMapperTest {

    companion object {
        private const val UNMAPPED_LOCALE_TAG = "fr-FR"
    }

    @Test
    fun `locale en_gb maps to english gb`() {
        val locale = Locale.forLanguageTag(LOCALE_UK_TAG)
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.EnglishGb)
    }

    @Test
    fun `locale gl_es maps to galician`() {
        val locale = Locale.forLanguageTag(LOCALE_GALICIA_TAG)
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.Galician)
    }

    @Test
    fun `locale es_es maps to spanish spain`() {
        val locale = Locale.forLanguageTag(LOCALE_SPAIN_TAG)
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.SpanishSpain)
    }

    @Test
    fun `unknown locale maps to system language`() {
        val locale = Locale.forLanguageTag(UNMAPPED_LOCALE_TAG)
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.System)
    }

    @Test
    fun `null locale maps to system language`() {
        val locale: Locale? = null
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.System)
    }

    @Test
    fun `english gb maps to locale list en_gb`() {
        val localeList = AppLanguage.EnglishGb.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_UK_TAG)
    }

    @Test
    fun `galician maps to locale list gl_es and es_es`() {
        val localeList = AppLanguage.Galician.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("${LOCALE_GALICIA_TAG},${LOCALE_SPAIN_TAG}")
    }

    @Test
    fun `spanish spain maps to locale list es_es`() {
        val localeList = AppLanguage.SpanishSpain.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(LOCALE_SPAIN_TAG)
    }

    @Test
    fun `system language maps to empty locale list`() {
        val localeList = AppLanguage.System.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("")
    }
}