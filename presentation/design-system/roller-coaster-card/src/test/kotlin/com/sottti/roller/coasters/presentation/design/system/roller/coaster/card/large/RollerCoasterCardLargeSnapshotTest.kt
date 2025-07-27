package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class RollerCoasterCardLargeSnapshotTest(
    nightMode: NightMode,
    private val state: RollerCoasterCardLargeState,
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
            RollerCoasterCardLargePreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            RollerCoasterCardLargePreviewProvider()
                .values
                .flatMap { state ->
                    listOf(
                        arrayOf(NightMode.NOTNIGHT, state as Any),
                        arrayOf(NightMode.NIGHT, state as Any),
                    )
                }
                .toList()
    }
}
