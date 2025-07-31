package com.sottti.roller.coasters.presentation.design.system.icons.pilledIcon

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconState
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconStateProvider
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
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
    val paparazzi = paparazzi(nightMode)

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