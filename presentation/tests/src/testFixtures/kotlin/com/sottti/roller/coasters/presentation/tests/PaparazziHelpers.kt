package com.sottti.roller.coasters.presentation.tests

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode

fun paparazzi(
    nightMode: NightMode,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.SHRINK,
): Paparazzi = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL_6_PRO.copy(nightMode = nightMode),
    renderingMode = renderingMode,
    showSystemUi = false,
    theme = "Theme.RollerCoasters",
)

fun nightModeParameters(): Collection<Array<Any>> = listOf(
    arrayOf(NightMode.NOTNIGHT),
    arrayOf(NightMode.NIGHT),
)

fun <T> nightModeParameters(states: Sequence<T>): Collection<Array<Any?>> =
    states.flatMap { state ->
        listOf(
            arrayOf(NightMode.NOTNIGHT, state),
            arrayOf(NightMode.NIGHT, state),
        )
    }.toList()
