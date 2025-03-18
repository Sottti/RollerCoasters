package com.sottti.roller.coasters.utils.device.mappers

import android.icu.util.LocaleData
import com.google.common.truth.Truth
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import org.junit.Test

internal class MeasurementSystemMapperTest {

    @Test
    fun siMapsToMetric() {
        val result = LocaleData.MeasurementSystem.SI.toSystemMeasurementSystem()
        Truth.assertThat(result).isEqualTo(SystemMeasurementSystem.Metric)
    }

    @Test
    fun ukMapsToImperialUk() {
        val result = LocaleData.MeasurementSystem.UK.toSystemMeasurementSystem()
        Truth.assertThat(result).isEqualTo(SystemMeasurementSystem.ImperialUk)
    }

    @Test
    fun usMapsToImperialUs() {
        val result = LocaleData.MeasurementSystem.US.toSystemMeasurementSystem()
        Truth.assertThat(result).isEqualTo(SystemMeasurementSystem.ImperialUs)
    }
}