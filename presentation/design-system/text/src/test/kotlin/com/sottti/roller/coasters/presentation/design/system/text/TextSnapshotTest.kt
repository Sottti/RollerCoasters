package com.sottti.roller.coasters.presentation.design.system.text

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import co.cuvva.roller.coasters.presentation.design.system.text.TextPreview
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class TextSnapshotTest(
    nightMode: NightMode,
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.Companion.PIXEL_6_PRO.copy(nightMode = nightMode),
        renderingMode = SessionParams.RenderingMode.SHRINK,
        showSystemUi = false,
        theme = "Theme.RollerCoasters",
    )

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            TextPreview()
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            listOf(
                arrayOf(NightMode.NOTNIGHT),
                arrayOf(NightMode.NIGHT),
            )
    }
}