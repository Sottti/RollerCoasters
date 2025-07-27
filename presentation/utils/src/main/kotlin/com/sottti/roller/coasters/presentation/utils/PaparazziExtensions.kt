package com.sottti.roller.coasters.presentation.utils

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.junit.Rule
import org.junit.Test

/**
 * Base class for Paparazzi snapshot tests to reduce boilerplate.
 */
abstract class BasePaparazziSnapshotTest<T>(
    nightMode: NightMode,
    private val state: T,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.NORMAL
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6_PRO.copy(nightMode = nightMode),
        renderingMode = renderingMode,
        showSystemUi = false,
        theme = "Theme.RollerCoasters",
    )

    /**
     * Implement this method with the composable that should be snapshotted.
     */
    protected abstract fun snapshotContent(state: T)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            snapshotContent(state)
        }
    }
}

/**
 * Helper to convert a [PreviewParameterProvider] into JUnit parameters including
 * light and dark [NightMode] variants.
 */
fun <T> PreviewParameterProvider<T>.paparazziParameters(): Collection<Array<Any?>> =
    values.flatMap { state ->
        listOf(
            arrayOf(NightMode.NOTNIGHT, state),
            arrayOf(NightMode.NIGHT, state)
        )
    }.toList()

