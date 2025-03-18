package com.sottti.roller.coasters.utils.device.managers

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.utils.device.mappers.LOCALE_GALICIA_TAG
import com.sottti.roller.coasters.utils.device.mappers.LOCALE_SPAIN_TAG
import com.sottti.roller.coasters.utils.device.mappers.LOCALE_UK_TAG
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
internal class LanguageManagerTest {

    private lateinit var languageManager: LanguageManager
    private lateinit var localeProvider: LocaleProvider

    @Before
    fun setup() {
        localeProvider = mockk()
        languageManager = LanguageManager(localeProvider)
        mockkStatic(AppCompatDelegate::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(AppCompatDelegate::class)
    }

    @Test
    fun testLocaleWhenAppLocaleAvailable() {
        val appLocale = Locale.forLanguageTag(LOCALE_UK_TAG)
        every { localeProvider.getAppLocale() } returns appLocale

        assertThat(languageManager.locale).isEqualTo(appLocale)
    }

    @Test
    fun testLocaleWhenAppLocaleIsNullUsesDefault() {
        val defaultLocale = Locale.forLanguageTag(LOCALE_SPAIN_TAG)
        every { localeProvider.getAppLocale() } returns defaultLocale

        assertThat(languageManager.locale).isEqualTo(defaultLocale)
    }

    @Test
    fun testLanguageWhenLocaleIsEnglishGb() {
        val locale = Locale.forLanguageTag(LOCALE_UK_TAG)
        every { localeProvider.getAppLocale() } returns locale

        assertThat(languageManager.language).isEqualTo(Language.EnglishGbLanguage)
    }

    @Test
    fun testLanguageWhenLocaleIsGalician() {
        val locale = Locale.forLanguageTag(LOCALE_GALICIA_TAG)
        every { localeProvider.getAppLocale() } returns locale

        assertThat(languageManager.language).isEqualTo(Language.GalicianLanguage)
    }

    @Test
    fun testLanguageWhenLocaleIsSpanishSpain() {
        val locale = Locale.forLanguageTag(LOCALE_SPAIN_TAG)
        every { localeProvider.getAppLocale() } returns locale

        assertThat(languageManager.language).isEqualTo(Language.SpanishSpainLanguage)
    }

    @Test
    fun testLanguageWhenLocaleIsUnsupported() {
        val locale = Locale.forLanguageTag("en-US")
        every { localeProvider.getAppLocale() } returns locale

        assertThat(languageManager.language).isEqualTo(Language.SystemLanguage)
    }

    @Test
    fun testLanguageWhenAppLocaleIsNullAndDefaultIsUnsupported() {
        val defaultLocale = Locale.forLanguageTag("fr-FR")
        every { localeProvider.getAppLocale() } returns defaultLocale

        assertThat(languageManager.language).isEqualTo(Language.SystemLanguage)
    }

    @Test
    fun testLanguageWhenAppLocaleIsNullAndDefaultIsSupported() {
        val defaultLocale = Locale.forLanguageTag(LOCALE_SPAIN_TAG)
        every { localeProvider.getAppLocale() } returns defaultLocale

        assertThat(languageManager.language).isEqualTo(Language.SpanishSpainLanguage)
    }

    @Test
    fun testSetLanguageEnglishGb() {
        val language = Language.EnglishGbLanguage
        val localeList = LocaleListCompat.forLanguageTags(LOCALE_UK_TAG)
        every { AppCompatDelegate.setApplicationLocales(localeList) } just runs

        languageManager.setLanguage(language)

        verify { AppCompatDelegate.setApplicationLocales(localeList) }
    }

    @Test
    fun testSetLanguageGalician() {
        val language = Language.GalicianLanguage
        val localeList = LocaleListCompat.forLanguageTags("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
        every { AppCompatDelegate.setApplicationLocales(localeList) } just runs

        languageManager.setLanguage(language)

        verify { AppCompatDelegate.setApplicationLocales(localeList) }
    }

    @Test
    fun testSetLanguageSpanishSpain() {
        val language = Language.SpanishSpainLanguage
        val localeList = LocaleListCompat.forLanguageTags(LOCALE_SPAIN_TAG)
        every { AppCompatDelegate.setApplicationLocales(localeList) } just runs

        languageManager.setLanguage(language)

        verify { AppCompatDelegate.setApplicationLocales(localeList) }
    }

    @Test
    fun testSetLanguageSystem() {
        val language = Language.SystemLanguage
        val localeList = LocaleListCompat.getEmptyLocaleList()
        every { AppCompatDelegate.setApplicationLocales(localeList) } just runs

        languageManager.setLanguage(language)

        verify { AppCompatDelegate.setApplicationLocales(localeList) }
    }
}