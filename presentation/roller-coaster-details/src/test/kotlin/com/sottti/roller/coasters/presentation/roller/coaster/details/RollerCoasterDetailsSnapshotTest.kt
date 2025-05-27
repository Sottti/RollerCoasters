package com.sottti.roller.coasters.presentation.roller.coaster.details

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsPreviewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.ui.RollerCoasterDetailsUiPreview
import com.sottti.roller.coasters.presentation.roller.coaster.details.ui.RollerCoasterDetailsUiPreviewProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

//@RunWith(Parameterized::class)
//internal class RollerCoasterDetailsSnapshotTest(
//    nightMode: NightMode,
//    private val state: RollerCoasterDetailsPreviewState,
//) {
//
//    @get:Rule
//    val paparazzi = Paparazzi(
//        deviceConfig = DeviceConfig.Companion.PIXEL_6_PRO.copy(nightMode = nightMode),
//        showSystemUi = false,
//        theme = "Theme.RollerCoasters",
//    )
//
//    @Test
//    fun snapshotTest() {
//        paparazzi.snapshot {
//            RollerCoasterDetailsUiPreview(state)
//        }
//    }
//
//    companion object {
//        @JvmStatic
//        @Parameterized.Parameters
//        fun data(): Collection<Array<Any>> =
//            RollerCoasterDetailsUiPreviewProvider()
//                .values
//                .flatMap { state ->
//                    listOf(
//                        arrayOf(NightMode.NOTNIGHT, state),
//                        arrayOf(NightMode.NIGHT, state),
//                    )
//                }
//                .toList()
//    }
//}