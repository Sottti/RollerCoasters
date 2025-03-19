package com.sottti.roller.coasters.utils.device.managers

import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem.Metric
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
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

    private lateinit var sdkFeatures: SdkFeatures
    private lateinit var localeManager: LocaleManager
    private lateinit var manager: MeasurementSystemManager

    @Before
    fun setup() {
        sdkFeatures = mockk()
        localeManager = mockk()
        manager = MeasurementSystemManager(sdkFeatures, localeManager)
        mockkStatic(LocaleData::class) // Removed ULocale mocking
    }

    @After
    fun tearDown() {
        unmockkStatic(LocaleData::class)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndMetric() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_SPAIN_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_SPAIN_TAG))
        } returns LocaleData.MeasurementSystem.SI

        assertThat(manager.measurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUk() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_UK_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_UK_TAG))
        } returns LocaleData.MeasurementSystem.UK

        assertThat(manager.measurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUs() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(LOCALE_US_TAG)
        every {
            LocaleData.getMeasurementSystem(ULocale(LOCALE_US_TAG))
        } returns LocaleData.MeasurementSystem.US

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUsRegion() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_US

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUkRegion() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_UK

        assertThat(manager.measurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndMetricRegion() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_SPAIN

        assertThat(manager.measurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndOtherImperialUsRegion() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns LOCALE_LIBERIA

        assertThat(manager.measurementSystem).isEqualTo(ImperialUs)
    }
}