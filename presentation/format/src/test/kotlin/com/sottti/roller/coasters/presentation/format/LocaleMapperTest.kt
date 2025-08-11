package com.sottti.roller.coasters.presentation.format

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.locales.localeEs
import com.sottti.roller.coasters.domain.locales.localeGb
import com.sottti.roller.coasters.domain.locales.localeGl
import com.sottti.roller.coasters.domain.locales.localeUs
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.format.mapper.toLocale
import org.junit.Test

internal class LocaleMapperTest {

    @Test
    fun `english gb maps to locale en_gb`() {
        val locale = AppLanguage.EnglishGb.toLocale(localeUs)
        assertThat(locale.toLanguageTag()).isEqualTo(localeGb.toLanguageTag())
    }

    @Test
    fun `galician maps to locale gl_es`() {
        val locale = AppLanguage.Galician.toLocale(localeUs)
        assertThat(locale.toLanguageTag()).isEqualTo(localeGl.toLanguageTag())
    }

    @Test
    fun `spanish spain maps to locale es_es`() {
        val locale = AppLanguage.SpanishSpain.toLocale(localeUs)
        assertThat(locale.toLanguageTag()).isEqualTo(localeEs.toLanguageTag())
    }

    @Test
    fun `system language maps to default locale`() {
        val defaultLocale = localeUs
        val locale = AppLanguage.System.toLocale(defaultLocale)
        assertThat(locale).isEqualTo(defaultLocale)
    }
}