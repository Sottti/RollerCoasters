package com.sottti.roller.coasters.utils.device

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import com.sottti.roller.coasters.utils.device.system.LocaleProvider
import com.sottti.roller.coasters.utils.device.system.SystemSettings
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
internal class SystemSettingsMeasurementSystemTest {

    private val localeSpain = Locale("es", "ES")
    private val localeUk = Locale.UK
    private val localeUs = Locale("en", "US")

    private val sdkFeatures = mockk<SdkFeatures>(relaxed = true)
    private val localeProvider = mockk<LocaleProvider>(relaxed = true)

    private fun setupLocale(appLocale: Locale?, defaultLocale: Locale = localeUs) {
        every { localeProvider.getAppLocale() } returns appLocale
        every { localeProvider.getDefaultLocale() } returns defaultLocale
    }

    @Test
    fun returnsImperialUs_whenLocaleIsUs_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(localeUs)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun returnsImperialUk_whenLocaleIsUk_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(localeUk)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUk)
    }

    @Test
    fun returnsMetric_whenLocaleIsSpain_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(localeSpain)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.Metric)
    }

    @Test
    fun returnsMetric_whenLocaleIsNull_andDefaultLocalIsSpain_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(null, localeSpain)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.Metric)
    }

    @Test
    fun returnsImperialUk_whenLocaleIsNull_andDefaultLocalIsUk_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(null, localeUk)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUk)
    }

    @Test
    fun returnsImperialUs_whenLocaleIsNull_andDefaultLocalIsUs_andFeatureUnavailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns false
        setupLocale(null, localeUs)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun returnsImperialUs_whenLocaleIsUs_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(localeUs)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun returnsImperialUk_whenLocaleIsUk_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(localeUk)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUk)
    }

    @Test
    fun returnsMetric_whenLocaleIsSpain_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(localeSpain)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.Metric)
    }

    @Test
    fun returnsMetric_whenLocaleIsNull_andDefaultLocalIsSpain_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(null, localeSpain)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.Metric)
    }

    @Test
    fun returnsImperialUk_whenLocaleIsNull_andDefaultLocalIsUk_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(null, localeUk)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUk)
    }

    @Test
    fun returnsImperialUs_whenLocaleIsNull_andDefaultLocalIsUs_andFeatureAvailable() {
        every { sdkFeatures.measurementSystemAvailable() } returns true
        setupLocale(null, localeUs)

        val systemSettings = SystemSettings(
            localeProvider = localeProvider,
            sdkFeatures = sdkFeatures,
            uiModeManager = null,
        )
        assertThat(systemSettings.measurementSystem)
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }
}