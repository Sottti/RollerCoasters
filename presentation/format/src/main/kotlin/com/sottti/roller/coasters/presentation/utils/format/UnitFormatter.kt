package com.sottti.roller.coasters.presentation.utils.format

import android.content.Context
import com.sottti.roller.coasters.domain.model.Feet
import com.sottti.roller.coasters.domain.model.Kmh
import com.sottti.roller.coasters.domain.model.Meters
import com.sottti.roller.coasters.domain.model.Mph
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.ImperialHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Height.MetricHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.format.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


public class UnitFormatter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        gForce: GForce,
    ): String = gForce.toDisplayFormatGForce(appLanguage)

    public fun toDisplayFormat(
        inversions: Inversions,
    ): String = inversions.toDisplayFormatInversions()

    public fun toDisplayFormat(
        maxVertical: MaxVertical,
    ): String = maxVertical.toDisplayFormatMaxVertical()

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        height: Height,
    ): String = when (height) {
        is ImperialHeight -> height.feet.toDisplayFormatFeet(appLanguage)
        is MetricHeight -> height.meters.toDisplayFormatMeters(appLanguage)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        drop: Drop,
    ): String = when (drop) {
        is Drop.ImperialDrop -> drop.feet.toDisplayFormatFeet(appLanguage)
        is Drop.MetricDrop -> drop.meters.toDisplayFormatMeters(appLanguage)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        length: Length,
    ): String = when (length) {
        is Length.ImperialLength -> length.feet.toDisplayFormatFeet(appLanguage)
        is Length.MetricLength -> length.meters.toDisplayFormatMeters(appLanguage)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        speed: Speed,
    ): String = when (speed) {
        is Speed.ImperialSpeed -> speed.mph.toDisplayFormatMph(appLanguage)
        is Speed.MetricSpeed -> speed.kmh.toDisplayFormatKmh(appLanguage)
    }

    private fun GForce.toDisplayFormatGForce(
        appLanguage: AppLanguage,
    ): String = context.getString(
        R.string.g_force, value.toDisplayFormat(appLanguage)
    )

    private fun Inversions.toDisplayFormatInversions(): String =
        context.getString(R.string.inversions, value)

    private fun MaxVertical.toDisplayFormatMaxVertical(): String =
        context.getString(R.string.max_vertical, degrees.value)

    private fun Feet.toDisplayFormatFeet(
        appLanguage: AppLanguage,
    ): String = context.getString(R.string.distance_feet, value.toDisplayFormat(appLanguage))

    private fun Meters.toDisplayFormatMeters(
        appLanguage: AppLanguage,
    ): String = context.getString(R.string.distance_meters, value.toDisplayFormat(appLanguage))

    private fun Mph.toDisplayFormatMph(
        appLanguage: AppLanguage
    ): String = context.getString(R.string.speed_mph, value.toDisplayFormat(appLanguage))

    private fun Kmh.toDisplayFormatKmh(
        appLanguage: AppLanguage
    ): String = context.getString(R.string.speed_kph, value.toDisplayFormat(appLanguage))
}