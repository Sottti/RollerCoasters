package com.sottti.roller.coasters.presentation.utils.format

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
internal class DisplayFormatTest {

    @Test
    fun formats_positive_number_with_English_GB_locale() {
        val result = 1234.567.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1,234.567")
    }

    @Test
    fun formats_positive_number_with_Galician_locale() {
        val result = 1234.567.toDisplayFormat(
            appLanguage = AppLanguage.Galician,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1.234,567")
    }

    @Test
    fun formats_positive_number_with_Spanish_Spain_locale() {
        val result = 1234.567.toDisplayFormat(
            appLanguage = AppLanguage.SpanishSpain,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1.234,567")
    }

    @Test
    fun formats_positive_number_with_System_locale_as_US() {
        val result = 1234.567.toDisplayFormat(
            appLanguage = AppLanguage.System,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1,234.567")
    }

    @Test
    fun formats_positive_number_with_System_locale_as_France() {
        val result = 1234.567.toDisplayFormat(
            appLanguage = AppLanguage.System,
            defaultLocale = Locale.FRANCE,
        )
        assertThat(result).isEqualTo("1\u202F234,567") // Narrow no-break space
    }

    @Test
    fun removes_trailing_zeros_after_decimal_in_English_GB() {
        val result = 123.500.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("123.5")
    }

    @Test
    fun removes_trailing_zeros_after_decimal_in_Galician() {
        val result = 123.500.toDisplayFormat(
            appLanguage = AppLanguage.Galician,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("123,5")
    }

    @Test
    fun simplifies_zero_with_trailing_zeros_in_English_GB() {
        val result = 0.000.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun simplifies_zero_with_trailing_zeros_in_Spanish_Spain() {
        val result = 0.000.toDisplayFormat(
            appLanguage = AppLanguage.SpanishSpain,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun formats_large_number_with_grouping_in_English_GB() {
        val result = 1234567.89.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1,234,567.89")
    }

    @Test
    fun formats_large_number_with_grouping_in_Galician() {
        val result = 1234567.89.toDisplayFormat(
            appLanguage = AppLanguage.Galician,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1.234.567,89")
    }

    @Test
    fun formats_negative_number_in_English_GB() {
        val result = (-123.45).toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("-123.45")
    }

    @Test
    fun formats_negative_number_in_Spanish_Spain() {
        val result = (-123.45).toDisplayFormat(
            appLanguage = AppLanguage.SpanishSpain,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("-123,45")
    }

    @Test
    fun limits_precision_to_3_digits_in_English_GB() {
        val result = 1.123456.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1.123")
    }

    @Test
    fun limits_precision_to_3_digits_in_Galician() {
        val result = 1.123456.toDisplayFormat(
            appLanguage = AppLanguage.Galician,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1,123")
    }

    @Test
    fun formats_very_small_number_in_English_GB() {
        val result = 0.001.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("0.001")
    }

    @Test
    fun formats_very_small_number_in_Spanish_Spain() {
        val result = 0.001.toDisplayFormat(
            appLanguage = AppLanguage.SpanishSpain,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("0,001")
    }

    @Test
    fun formats_integer_in_English_GB() {
        val result = 1234.0.toDisplayFormat(
            appLanguage = AppLanguage.EnglishGb,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1,234")
    }

    @Test
    fun formats_integer_in_Galician() {
        val result = 1234.0.toDisplayFormat(
            appLanguage = AppLanguage.Galician,
            defaultLocale = Locale.US,
        )
        assertThat(result).isEqualTo("1.234")
    }
}