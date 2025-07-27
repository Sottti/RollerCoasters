package com.sottti.roller.coasters.presentation.design.system.icons.circledIcon

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnBackgroundPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.circledIcon.CircledIconOnSurfacePreview
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class CircledIconSnapshotTest(
    nightMode: NightMode,
) : BasePaparazziSnapshotTest<Unit>(
    nightMode,
    Unit,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

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
        fun data(): Collection<Array<Any>> =
            listOf(
                arrayOf(NightMode.NOTNIGHT),
                arrayOf(NightMode.NIGHT),
            )
    }
}
