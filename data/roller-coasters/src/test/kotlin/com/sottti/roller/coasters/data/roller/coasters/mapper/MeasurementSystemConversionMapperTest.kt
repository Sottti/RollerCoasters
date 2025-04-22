package com.sottti.roller.coasters.data.roller.coasters.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Mph
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.ImperialDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop.MetricDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.ImperialHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.MetricHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.ImperialLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Length.MetricLength
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.ImperialSpeed
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed.MetricSpeed
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import org.junit.Test

internal class MeasurementSystemConversionMapperTest {

    @Test
    fun `converting speed for metric system returns metric speed`() {
        val result = 100.0.toSpeed(Metric)
        assertThat(result).isInstanceOf(MetricSpeed::class.java)
    }

    @Test
    fun `converting speed for US imperial system returns imperial speed`() {
        val result = 100.0.toSpeed(ImperialUs)
        assertThat(result).isInstanceOf(ImperialSpeed::class.java)
    }

    @Test
    fun `converting speed for UK imperial system returns imperial speed`() {
        val result = 100.0.toSpeed(ImperialUk)
        assertThat(result).isInstanceOf(ImperialSpeed::class.java)
    }

    @Test
    fun `converting height for metric system returns metric height`() {
        val result = 50.0.toHeight(Metric)
        assertThat(result).isInstanceOf(MetricHeight::class.java)
    }

    @Test
    fun `converting height for US imperial system returns imperial height`() {
        val result = 50.0.toHeight(ImperialUs)
        assertThat(result).isInstanceOf(ImperialHeight::class.java)
    }

    @Test
    fun `converting height for UK imperial system returns imperial height`() {
        val result = 50.0.toHeight(ImperialUk)
        assertThat(result).isInstanceOf(ImperialHeight::class.java)
    }

    @Test
    fun `converting drop for metric system returns metric drop`() {
        val result = 30.0.toDrop(Metric)
        assertThat(result).isInstanceOf(MetricDrop::class.java)
    }

    @Test
    fun `converting drop for US imperial system returns imperial drop`() {
        val result = 30.0.toDrop(ImperialUs)
        assertThat(result).isInstanceOf(ImperialDrop::class.java)
    }

    @Test
    fun `converting drop for UK imperial system returns imperial drop`() {
        val result = 30.0.toDrop(ImperialUk)
        assertThat(result).isInstanceOf(ImperialDrop::class.java)
    }

    @Test
    fun `converting length for metric system returns metric length`() {
        val result = 1000.0.toLength(Metric)
        assertThat(result).isInstanceOf(MetricLength::class.java)
    }

    @Test
    fun `converting length for US imperial system returns imperial length`() {
        val result = 1000.0.toLength(ImperialUs)
        assertThat(result).isInstanceOf(ImperialLength::class.java)
    }

    @Test
    fun `converting length for UK imperial system returns imperial length`() {
        val result = 1000.0.toLength(ImperialUk)
        assertThat(result).isInstanceOf(ImperialLength::class.java)
    }

    @Test
    fun `converting imperial drop to metric returns metric drop`() {
        val imperialDrop = ImperialDrop(Feet(100.0))
        val result = imperialDrop.toMetric()
        assertThat(result).isInstanceOf(MetricDrop::class.java)
    }

    @Test
    fun `converting metric drop to metric returns metric drop`() {
        val metricDrop = MetricDrop(Meters(30.0))
        val result = metricDrop.toMetric()
        assertThat(result).isInstanceOf(MetricDrop::class.java)
    }

    @Test
    fun `converting imperial length to metric returns metric length`() {
        val imperialLength = ImperialLength(Feet(1000.0))
        val result = imperialLength.toMetric()
        assertThat(result).isInstanceOf(MetricLength::class.java)
    }

    @Test
    fun `converting metric length to metric returns metric length`() {
        val metricLength = MetricLength(Meters(300.0))
        val result = metricLength.toMetric()
        assertThat(result).isInstanceOf(MetricLength::class.java)
    }

    @Test
    fun `converting imperial height to metric returns metric height`() {
        val imperialHeight = ImperialHeight(Feet(200.0))
        val result = imperialHeight.toMetric()
        assertThat(result).isInstanceOf(MetricHeight::class.java)
    }

    @Test
    fun `converting metric height to metric returns metric height`() {
        val metricHeight = MetricHeight(Meters(60.0))
        val result = metricHeight.toMetric()
        assertThat(result).isInstanceOf(MetricHeight::class.java)
    }

    @Test
    fun `converting imperial speed to metric returns metric speed`() {
        val imperialSpeed = ImperialSpeed(Mph(60.0))
        val result = imperialSpeed.toMetric()
        assertThat(result).isInstanceOf(MetricSpeed::class.java)
    }

    @Test
    fun `converting metric speed to metric returns metric speed`() {
        val metricSpeed = MetricSpeed(Kmh(100.0))
        val result = metricSpeed.toMetric()
        assertThat(result).isInstanceOf(MetricSpeed::class.java)
    }
}