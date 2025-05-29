package com.sottti.roller.coasters.presentation.settings.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SettingsUiPickersSnapshotTest(
    nightMode: NightMode,
    private val state: SettingsPreviewState,
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.Companion.PIXEL_6_PRO.copy(nightMode = nightMode),
        showSystemUi = false,
        theme = "Theme.RollerCoasters",
    )

    @Test
    fun snapshotPickersTest() {
        paparazzi.snapshot {
            SettingsUiPickersPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            SettingsUiPickersStateProvider()
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