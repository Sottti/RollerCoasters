package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.small

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class RollerCoasterCardSmallSnapshotTest(
    nightMode: NightMode,
    state: RollerCoasterCardSmallState,
) : BasePaparazziSnapshotTest<RollerCoasterCardSmallState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: RollerCoasterCardSmallState) {
        RollerCoasterCardSmallPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            RollerCoasterCardSmallPreviewProvider().paparazziParameters()
    }
}
