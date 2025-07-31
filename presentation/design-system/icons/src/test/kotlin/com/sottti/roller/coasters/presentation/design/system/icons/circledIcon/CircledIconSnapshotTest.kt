package com.sottti.roller.coasters.presentation.design.system.icons.circledIcon

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnBackgroundPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnSurfacePreview
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class CircledIconSnapshotTest(
    nightMode: NightMode,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotOnBackgroundTest() {
        paparazzi.snapshot {
            CircledIconOnBackgroundPreview()
        }
    }

    @Test
    fun snapshotOnSurfaceTest() {
        paparazzi.snapshot {
            CircledIconOnSurfacePreview()
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = nightModeParameters()
    }
}