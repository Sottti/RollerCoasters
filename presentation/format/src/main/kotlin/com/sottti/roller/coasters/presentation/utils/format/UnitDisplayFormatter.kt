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
import java.util.Locale
import javax.inject.Inject


public class UnitDisplayFormatter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
        gForce: GForce,
    ): String = gForce.toDisplayFormatGForce(appLanguage, defaultLocale)

    public fun toDisplayFormat(
        inversions: Inversions,
    ): String = inversions.toDisplayFormatInversions()

    public fun toDisplayFormat(
        maxVertical: MaxVertical,
    ): String = maxVertical.toDisplayFormatMaxVertical()

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
        height: Height,
    ): String = when (height) {
        is ImperialHeight -> height.feet.toDisplayFormatFeet(appLanguage, defaultLocale)
        is MetricHeight -> height.meters.toDisplayFormatMeters(appLanguage, defaultLocale)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
        drop: Drop,
    ): String = when (drop) {
        is Drop.ImperialDrop -> drop.feet.toDisplayFormatFeet(appLanguage, defaultLocale)
        is Drop.MetricDrop -> drop.meters.toDisplayFormatMeters(appLanguage, defaultLocale)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
        length: Length,
    ): String = when (length) {
        is Length.ImperialLength -> length.feet.toDisplayFormatFeet(appLanguage, defaultLocale)
        is Length.MetricLength -> length.meters.toDisplayFormatMeters(appLanguage, defaultLocale)
    }

    public fun toDisplayFormat(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
        speed: Speed,
    ): String = when (speed) {
        is Speed.ImperialSpeed -> speed.mph.toDisplayFormatMph(appLanguage, defaultLocale)
        is Speed.MetricSpeed -> speed.kmh.toDisplayFormatKmh(appLanguage, defaultLocale)
    }

    private fun GForce.toDisplayFormatGForce(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
    ): String = context.getString(
        R.string.g_force, value.toDisplayFormat(appLanguage, defaultLocale)
    )

    private fun Inversions.toDisplayFormatInversions(): String =
        context.getString(R.string.inversions, value)

    private fun MaxVertical.toDisplayFormatMaxVertical(): String =
        context.getString(R.string.max_vertical, degrees.value)

    private fun Feet.toDisplayFormatFeet(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
    ): String = context.getString(
        R.string.distance_feet,
        value.toDisplayFormat(appLanguage, defaultLocale)
    )

    private fun Meters.toDisplayFormatMeters(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
    ): String = context.getString(
        R.string.distance_meters,
        value.toDisplayFormat(appLanguage, defaultLocale)
    )

    private fun Mph.toDisplayFormatMph(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
    ): String = context.getString(
        R.string.speed_mph,
        value.toDisplayFormat(appLanguage, defaultLocale)
    )

    private fun Kmh.toDisplayFormatKmh(
        appLanguage: AppLanguage,
        defaultLocale: Locale,
    ): String = context.getString(
        R.string.speed_kph,
        value.toDisplayFormat(appLanguage, defaultLocale)
    )
}