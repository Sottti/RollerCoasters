package com.sottti.roller.coasters.presentation.design.system.chip

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6_PRO
import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ChipSnapshotTest(
    nightMode: NightMode,
    state: ChipState,
) : BasePaparazziSnapshotTest<ChipState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: ChipState) {
        ChipPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            ChipPreviewProvider().paparazziParameters()
    }
}
