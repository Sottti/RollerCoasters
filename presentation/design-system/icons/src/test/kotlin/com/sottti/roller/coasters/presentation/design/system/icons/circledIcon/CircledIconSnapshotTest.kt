package com.sottti.roller.coasters.presentation.design.system.icons.circledIcon

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sotti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnBackgroundPreview
import com.sotti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnSurfacePreview
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class CircledIconSnapshotTest(
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
    fun snapshotOnBackgroundTest() {
        paparazzi.snapshot {
            CircledIconOnBackgroundPreview()
        }
    }

    @Test
    fun snapshotOnSurfaceTest() {
        paparazzi.snapshot {
            CircledIconOnSurfacePreview()
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