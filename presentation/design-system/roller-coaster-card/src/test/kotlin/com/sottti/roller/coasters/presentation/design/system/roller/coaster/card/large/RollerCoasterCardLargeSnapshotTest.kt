package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class RollerCoasterCardLargeSnapshotTest(
    nightMode: NightMode,
    state: RollerCoasterCardLargeState,
) : BasePaparazziSnapshotTest<RollerCoasterCardLargeState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: RollerCoasterCardLargeState) {
        RollerCoasterCardLargePreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            RollerCoasterCardLargePreviewProvider().paparazziParameters()
    }
}
