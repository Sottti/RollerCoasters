package com.sottti.roller.coasters.data.settings.managers

import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.locales.localeEs
import com.sottti.roller.coasters.domain.locales.localeGb
import com.sottti.roller.coasters.domain.locales.localeLr
import com.sottti.roller.coasters.domain.locales.localeUs
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

@RunWith(AndroidJUnit4::class)
internal class MeasurementSystemManagerTest {
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
        every { localeManager.systemULocale } returns ULocale(localeEs.toLanguageTag())
        every {
            LocaleData.getMeasurementSystem(ULocale(localeEs.toLanguageTag()))
        } returns LocaleData.MeasurementSystem.SI

        assertThat(manager.systemMeasurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUk() {
        every { features.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(localeGb.toLanguageTag())
        every {
            LocaleData.getMeasurementSystem(ULocale(localeGb.toLanguageTag()))
        } returns LocaleData.MeasurementSystem.UK

        assertThat(manager.systemMeasurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureAvailableAndImperialUs() {
        every { features.measurementSystemAvailable() } returns true
        every { localeManager.systemULocale } returns ULocale(localeUs.toLanguageTag())
        every {
            LocaleData.getMeasurementSystem(ULocale(localeUs.toLanguageTag()))
        } returns LocaleData.MeasurementSystem.US

        assertThat(manager.systemMeasurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUsRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns localeUs

        assertThat(manager.systemMeasurementSystem).isEqualTo(ImperialUs)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndImperialUkRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns localeGb

        assertThat(manager.systemMeasurementSystem).isEqualTo(ImperialUk)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndMetricRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns localeEs

        assertThat(manager.systemMeasurementSystem).isEqualTo(Metric)
    }

    @Test
    fun testMeasurementSystemWhenFeatureUnavailableAndOtherImperialUsRegion() {
        every { features.measurementSystemAvailable() } returns false
        every { localeManager.systemLocale } returns localeLr

        assertThat(manager.systemMeasurementSystem).isEqualTo(ImperialUs)
    }
}