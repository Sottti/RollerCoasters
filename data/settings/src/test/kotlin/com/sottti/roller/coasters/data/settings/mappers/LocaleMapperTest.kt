package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.toLanguage
import com.sottti.roller.coasters.data.settings.mapper.toLocaleList
import com.sottti.roller.coasters.domain.locales.localeEs
import com.sottti.roller.coasters.domain.locales.localeFr
import com.sottti.roller.coasters.domain.locales.localeGb
import com.sottti.roller.coasters.domain.locales.localeGl
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import org.junit.Test
import java.util.Locale

internal class LocaleMapperTest {

    @Test
    fun `locale en_gb maps to english gb`() {
        val locale = localeGb
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.EnglishGb)
    }

    @Test
    fun `locale gl_es maps to galician`() {
        val locale = localeGl
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.Galician)
    }

    @Test
    fun `locale es_es maps to spanish spain`() {
        val locale = localeEs
        assertThat(locale.toLanguage()).isEqualTo(AppLanguage.SpanishSpain)
    }

    @Test
    fun `unknown locale maps to system language`() {
        val locale = localeFr
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
        assertThat(localeList.toLanguageTags()).isEqualTo(localeGb.toLanguageTag())
    }

    @Test
    fun `galician maps to locale list gl_es and es_es`() {
        val localeList = AppLanguage.Galician.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("${localeGl.toLanguageTag()},${localeEs.toLanguageTag()}")
    }

    @Test
    fun `spanish spain maps to locale list es_es`() {
        val localeList = AppLanguage.SpanishSpain.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo(localeEs.toLanguageTag())
    }

    @Test
    fun `system language maps to empty locale list`() {
        val localeList = AppLanguage.System.toLocaleList()
        assertThat(localeList.toLanguageTags()).isEqualTo("")
    }
}