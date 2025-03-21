package com.sottti.roller.coasters.utils.device.managers

import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
internal class MeasurementSystemManagerTest {
    private companion object {
        private const val LOCALE_SPAIN_TAG = "es-ES"
        private const val LOCALE_UK_TAG = "en-GB"
        private const val LOCALE_US_TAG = "en-US"

        private val LOCALE_SPAIN = Locale("es", "ES")
        private val LOCALE_UK = Locale("en", "GB")
        private val LOCALE_US = Locale("en", "US")
        private val LOCALE_LIBERIA = Locale("en", "LR")
    }

    private lateinit var features: Features
    private lateinit var localeManager: LocaleManager
    private lateinit var manager: MeasurementSystemManager

    @Before
    fun setup() {
        features = mockk()
        localeManager = mockk()
        manager = MeasurementSystemManager(features, localeManager)
        mockkStatic(LocaleData::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(LocaleData::class)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndMetric() {
        every { features.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_SPAIN_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_SPAIN_TAG))
        } returns LocaleData.MeasurementSystem.SI

        assertThat(manager.measurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUk() {
        every { features.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_UK_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_UK_TAG))
        } returns LocaleData.MeasurementSystem.UK

        assertThat(manager.measurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUs() {
        every { features.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_US_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_US_TAG))
        } returns LocaleData.MeasurementSystem.US

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUsRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_US

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUkRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_UK

        assertThat(manager.measurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndMetricRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_SPAIN

        assertThat(manager.measurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndOtherImperialUsRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_LIBERIA

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }
}