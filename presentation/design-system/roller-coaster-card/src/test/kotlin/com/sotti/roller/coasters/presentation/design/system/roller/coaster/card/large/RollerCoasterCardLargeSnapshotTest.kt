package com.sotti.roller.coasters.presentation.design.system.roller.coaster.card.large

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sotti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sotti.roller.coasters.presentation.paparazzi.paparazzi
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
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            RollerCoasterCardLargePreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(RollerCoasterCardLargeStateProvider().values)
    }
}
