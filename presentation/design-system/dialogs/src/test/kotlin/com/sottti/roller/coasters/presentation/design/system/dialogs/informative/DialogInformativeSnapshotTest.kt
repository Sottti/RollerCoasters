package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class DialogInformativeSnapshotTest(
    nightMode: NightMode,
    private val state: DialogInformativeState,
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
            RollerCoastersPreviewTheme {
                DialogInformativePreview(state)
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            DialogInformativePreviewProvider()
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