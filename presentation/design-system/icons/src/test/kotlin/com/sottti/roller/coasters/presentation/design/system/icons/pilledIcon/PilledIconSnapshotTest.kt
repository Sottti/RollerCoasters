package com.sottti.roller.coasters.presentation.design.system.icons.pilledIcon

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconPreviewProvider
import com.sottti.roller.coasters.presentation.design.system.icons.ui.pilledIcon.PilledIconPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class PilledIconSnapshotTest(
    nightMode: NightMode,
    state: PilledIconPreviewState,
) : BasePaparazziSnapshotTest<PilledIconPreviewState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: PilledIconPreviewState) {
        PilledIconPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            PilledIconPreviewProvider().paparazziParameters()
    }
}
