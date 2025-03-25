package com.sottti.roller.coasters.presentation.utils.format

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.utils.format.mapper.LOCALE_GALICIA_TAG
import com.sottti.roller.coasters.presentation.utils.format.mapper.LOCALE_SPAIN_TAG
import com.sottti.roller.coasters.presentation.utils.format.mapper.LOCALE_UK_TAG
import com.sottti.roller.coasters.presentation.utils.format.mapper.toLocale
import org.junit.Test
import java.util.Locale

internal class LocaleMapperTest {

    @Test
    fun `english gb maps to locale en_gb`() {
        val locale = AppLanguage.EnglishGb.toLocale()
        assertThat(locale.toLanguageTag()).isEqualTo(LOCALE_UK_TAG)
    }

    @Test
    fun `galician maps to locale gl_es`() {
        val locale = AppLanguage.Galician.toLocale()
        assertThat(locale.toLanguageTag()).isEqualTo(LOCALE_GALICIA_TAG)
    }

    @Test
    fun `spanish spain maps to locale es_es`() {
        val locale = AppLanguage.SpanishSpain.toLocale()
        assertThat(locale.toLanguageTag()).isEqualTo(LOCALE_SPAIN_TAG)
    }

    @Test
    fun `system language maps to default locale`() {
        val default = Locale.getDefault()
        val locale = AppLanguage.System.toLocale()
        assertThat(locale).isEqualTo(default)
    }
}