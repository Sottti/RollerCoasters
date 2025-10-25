package com.sotti.roller.coasters.presentation.design.system.switchh

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sotti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sotti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SwitchSnapshotTest(
    nightMode: NightMode,
    private val state: SwitchState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            SwitchPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(SwitchStateProvider().values)
    }
}
