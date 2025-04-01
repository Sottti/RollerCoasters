package com.sottti.roller.coasters.presentation.format

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
internal class DisplayFormatTest {

    @Test
    fun to_display_format_integer_returnsCorrect_inEnglishGb() {
        val result = 1234.0.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,234")
    }

    @Test
    fun to_display_format_integer_returnsCorrect_inGalician() {
        val result = 1234.0.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("1.234")
    }

    @Test
    fun to_display_format_decimal_returnsCorrect_inGalician() {
        val result = 250.5.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("250,5")
    }

    @Test
    fun to_display_format_decimal_returnsCorrect_inEnglishGb() {
        val result = 123.5.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("123.5")
    }

    @Test
    fun to_display_format_zero_returnsCorrect_inEnglishGb() {
        val result = 0.0.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun to_display_format_zero_returnsCorrect_inSpanishSpain() {
        val result = 0.0.toDisplayFormat(AppLanguage.SpanishSpain, Locale.US)
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun to_display_format_positiveNumber_formatsCorrectly_inEnglishGb() {
        val result = 1234.567.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,234.567")
    }

    @Test
    fun to_display_format_positiveNumber_formatsCorrectly_inGalician() {
        val result = 1234.567.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("1.234,567")
    }

    @Test
    fun to_display_format_positiveNumber_formatsCorrectly_inSpanishSpain() {
        val result = 1234.567.toDisplayFormat(AppLanguage.SpanishSpain, Locale.US)
        assertThat(result).isEqualTo("1.234,567")
    }

    @Test
    fun to_display_format_positiveNumber_formatsCorrectly_withSystemLocaleUs() {
        val result = 1234.567.toDisplayFormat(AppLanguage.System, Locale.US)
        assertThat(result).isEqualTo("1,234.567")
    }

    @Test
    fun to_display_format_positiveNumber_formatsCorrectly_withSystemLocaleFrance() {
        val result = 1234.567.toDisplayFormat(AppLanguage.System, Locale.FRANCE)
        assertThat(result).isEqualTo("1\u202F234,567")
    }

    @Test
    fun to_display_format_removesTrailingZeros_inEnglishGb() {
        val result = 123.500.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("123.5")
    }

    @Test
    fun to_display_format_removesTrailingZeros_inGalician() {
        val result = 123.500.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("123,5")
    }

    @Test
    fun to_display_format_removesTrailingZeros_inThreeDecimalPlaces_inEnglishGb() {
        val result = 1234.500.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,234.5")
    }

    @Test
    fun to_display_format_removesTrailingZeros_fromLargeNumber_inEnglishGb() {
        val result = 1000.2000.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,000.2")
    }

    @Test
    fun to_display_format_largeNumber_formatsCorrectly_inEnglishGb() {
        val result = 1234567.89.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,234,567.89")
    }

    @Test
    fun to_display_format_largeNumber_formatsCorrectly_inGalician() {
        val result = 1234567.89.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("1.234.567,89")
    }

    @Test
    fun to_display_format_largeNumber_formatsCorrectly_inSpanishSpain() {
        val result = 1234567.89.toDisplayFormat(AppLanguage.SpanishSpain, Locale.US)
        assertThat(result).isEqualTo("1.234.567,89")
    }

    @Test
    fun to_display_format_largeNumber_withoutDecimals_formatsCorrectly_inGalician() {
        val result = 12345.0.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("12.345")
    }

    @Test
    fun to_display_format_limitsPrecisionToThreeDigits_inEnglishGb() {
        val result = 1.123456.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1.123")
    }

    @Test
    fun to_display_format_limitsPrecisionToThreeDigits_inGalician() {
        val result = 1.123456.toDisplayFormat(AppLanguage.Galician, Locale.US)
        assertThat(result).isEqualTo("1,123")
    }

    @Test
    fun to_display_format_roundsUpCorrectly_inEnglishGb() {
        val result = 999.9999.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("1,000")
    }

    @Test
    fun to_display_format_negativeNumber_formatsCorrectly_inEnglishGb() {
        val result = (-123.45).toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("-123.45")
    }

    @Test
    fun to_display_format_negativeNumber_formatsCorrectly_inSpanishSpain() {
        val result = (-123.45).toDisplayFormat(AppLanguage.SpanishSpain, Locale.US)
        assertThat(result).isEqualTo("-123,45")
    }

    @Test
    fun to_display_format_verySmallNumber_formatsCorrectly_inEnglishGb() {
        val result = 0.001.toDisplayFormat(AppLanguage.EnglishGb, Locale.US)
        assertThat(result).isEqualTo("0.001")
    }

    @Test
    fun to_display_format_verySmallNumber_formatsCorrectly_inSpanishSpain() {
        val result = 0.001.toDisplayFormat(AppLanguage.SpanishSpain, Locale.US)
        assertThat(result).isEqualTo("0,001")
    }
}