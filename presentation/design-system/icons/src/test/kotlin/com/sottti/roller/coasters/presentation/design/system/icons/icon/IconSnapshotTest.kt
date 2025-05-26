package com.sottti.roller.coasters.presentation.design.system.icons.icon

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreview
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewProvider
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewState
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IconSnapshotTest(
    nightMode: NightMode,
    private val state: IconPreviewState,
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
            IconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            IconPreviewProvider()
                .values
                .flatMap { state ->
                    listOf(
                        arrayOf(NightMode.NOTNIGHT, state),
                        arrayOf(NightMode.NIGHT, state),
                    )
                }
                .toList()
    }
}