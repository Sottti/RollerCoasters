package com.sottti.roller.coasters.presentation.design.system.icons.pilledIcon

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconStateProvider
import com.sottti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sottti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class PilledIconSnapshotTest(
    nightMode: NightMode,
    private val state: PilledIconState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            PilledIconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(PilledIconStateProvider().values)
    }
}