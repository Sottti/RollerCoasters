package com.sottti.roller.coasters.presentation.design.system.icons.pilledIcon

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import co.cuvva.presentation.design.system.icons.ui.icon.IconPreviewProvider
import co.cuvva.presentation.design.system.icons.ui.pilledIcon.PilledIconPreview
import co.cuvva.presentation.design.system.icons.ui.pilledIcon.PilledIconPreviewProvider
import co.cuvva.presentation.design.system.icons.ui.pilledIcon.PilledIconPreviewState
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class PilledIconSnapshotTest(
    nightMode: NightMode,
    private val state: PilledIconPreviewState,
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
            PilledIconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            PilledIconPreviewProvider()
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