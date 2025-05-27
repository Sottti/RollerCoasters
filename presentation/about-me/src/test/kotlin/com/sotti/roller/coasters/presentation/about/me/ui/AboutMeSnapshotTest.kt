package com.sotti.roller.coasters.presentation.about.me.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUiPreview
import com.sottti.roller.coasters.presentation.about.me.ui.AboutMeUiStateProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class AboutMeSnapshotTest(
    nightMode: NightMode,
    private val state: AboutMePreviewState,
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.Companion.PIXEL_6_PRO.copy(nightMode = nightMode),
        showSystemUi = false,
        theme = "Theme.RollerCoasters",
    )

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            AboutMeUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            AboutMeUiStateProvider()
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